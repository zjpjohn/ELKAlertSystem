package com.alert.email;

import com.alert.config.RedissonCache;
import com.alert.domain.SenderEmail;
import org.redisson.api.RAtomicLong;
import org.redisson.api.RMap;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Project: AlertSystem
 * Module Desc:com.juntu.alert.email
 * User: zjprevenge
 * Date: 2016/11/9
 * Time: 14:19
 */
@Component
public class EmailSenderCache implements InitializingBean {

    @Resource
    private RedissonCache redissonCache;

    //进行负载均衡
    //未被选择使用的邮件列表
    private RMap<Integer, SenderEmail> unselected;
    //已经选择使用的邮件列表
    private RMap<Integer, SenderEmail> selected;

    //作为被选择邮件列表，0-unselected；1-selected
    private RAtomicLong tag;

    /**
     * 将邮箱添加到未发送列表
     *
     * @param senderEmail 邮箱
     */
    public synchronized void putEmail(SenderEmail senderEmail) {
        if (tag.get() == 0) {
            unselected.put(senderEmail.getId(), senderEmail);
        } else {
            selected.put(senderEmail.getId(), senderEmail);
        }
    }

    /**
     * 负载均衡获取一个邮箱
     *
     * @return 邮箱
     */
    public synchronized SenderEmail getEmail() {
        SenderEmail senderEmail = null;
        Iterator<Map.Entry<Integer, SenderEmail>> iterator;
        if (tag.get() == 0) {
            iterator = unselected.entrySet().iterator();
            if (iterator.hasNext()) {
                senderEmail = iterator.next().getValue();
                unselected.remove(senderEmail.getId());
                selected.put(senderEmail.getId(), senderEmail);
            }
            if (unselected.size() == 0) {
                tag.set(1);
            }
            return senderEmail;
        } else {
            iterator = selected.entrySet().iterator();
            if (iterator.hasNext()) {
                senderEmail = iterator.next().getValue();
                selected.remove(senderEmail.getId());
                unselected.put(senderEmail.getId(), senderEmail);
            }
            if (selected.size() == 0) {
                tag.set(0);
            }
            return senderEmail;
        }
    }

    /**
     * 清楚无效缓存
     *
     * @param id
     */
    public synchronized void invalidSendEmail(Integer id) {
        unselected.remove(id);
        selected.remove(id);
    }

    public synchronized void putAll(List<SenderEmail> senderEmails) {
        for (SenderEmail senderEmail : senderEmails) {
            putEmail(senderEmail);
        }
    }

    public synchronized SenderEmail get(Integer id) {
        SenderEmail senderEmail = unselected.get(id);
        if (senderEmail == null) {
            senderEmail = selected.get(id);
        }
        return senderEmail;
    }

    /**
     * 判断缓存为空
     *
     * @return
     */
    public boolean isEmpty() {
        return unselected.isEmpty() && selected.isEmpty();
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        unselected = redissonCache.unselectedSender();
        selected = redissonCache.selectedSender();
        tag = redissonCache.senderTag();
    }

    //标识重置
    public void reset() {
        unselected.clear();
        selected.clear();
        tag.set(0);
    }
}
