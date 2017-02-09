package com.alert.rule;

import com.alert.config.RedissonCache;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Project: system
 * Module Desc:com.juntu.alert.rule
 * User: zjprevenge
 * Date: 2016/12/20
 * Time: 17:01
 * <p>
 * 分步式统计，主要解决多台机器统一计数,准确进行消息发送
 */
@Service
public class DistributeRateCountMap implements InitializingBean {

    private static final Logger log = LoggerFactory.getLogger(DistributeRateCountMap.class);

    @Resource
    private RedissonCache redissonCache;

    /**
     * 统计消息频率
     *
     * @param message  消息
     * @param express  异常
     * @param rate     频率
     * @param interval 时间间隔
     * @return true-发送邮件;false-不发送邮件
     */
    public boolean countRate(Message message,
                             String express,
                             Integer rate,
                             Integer interval) {
        redissonCache.countLock().lock();
        try {
            String key = message.getType() + ":" + express;
            //获取满足条件的日志统计次数
            Integer rateCount = redissonCache.rateMap().get(key);
            //开始时间
            Long startTime = redissonCache.timeMap().get(key);
            //如果消息首次出现
            if (startTime == null) {
                rateCount = 1;
                redissonCache.timeMap().put(key, System.currentTimeMillis());
                redissonCache.rateMap().put(key, rateCount);
                redissonCache.alertMap().put(key, false);
                return false;
            }
            //统计次数增加
            int count = rateCount++;
            //计算到达时间
            long arriveTime = System.currentTimeMillis();
            //频率未达到阈值
            if (count < rate) {
                //未达到时间间隔
                if ((arriveTime - startTime) < interval * 1000) {
                    redissonCache.rateMap().put(key, rateCount);
                } else {
                    //已经达到时间间隔
                    redissonCache.rateMap().put(key, 1);
                    redissonCache.timeMap().put(key, System.currentTimeMillis());
                    //如果已经发过，将标识置为false
                    if (redissonCache.alertMap().get(key)) {
                        redissonCache.alertMap().put(key, false);
                    }
                }
                return false;
            } else {
                //在时间阈值内出现满足条件次数，进行报警
                if ((arriveTime - startTime) < interval * 1000) {
                    redissonCache.rateMap().put(key, 1);
                    redissonCache.timeMap().put(key, System.currentTimeMillis());
                    //如果已经报过警了
                    if (redissonCache.alertMap().get(key)) {
                        return false;
                    }
                    redissonCache.alertMap().put(key, true);
                    return true;
                } else {//在时间阈值内未出现满足条件的次数，不进行报警
                    redissonCache.rateMap().put(key, 1);
                    redissonCache.timeMap().put(key, System.currentTimeMillis());
                    //如果已经发过，将标识置为false
                    if (redissonCache.alertMap().get(key)) {
                        redissonCache.alertMap().put(key, false);
                    }
                    return false;
                }
            }
        } catch (Exception e) {
            log.error("count occur error:{}", e);
        } finally {
            redissonCache.countLock().unlock();
        }
        return false;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        redissonCache.timeMap().clear();
    }
}
