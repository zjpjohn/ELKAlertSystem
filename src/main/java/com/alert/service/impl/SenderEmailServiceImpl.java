package com.alert.service.impl;

import com.alert.domain.SenderEmail;
import com.alert.email.EmailSenderCache;
import com.alert.mapper.SenderEmailMapper;
import com.google.common.base.Preconditions;
import com.alert.service.SenderEmailService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * Project: AlertSystem
 * Module Desc:com.juntu.alert.service.impl
 * User: zjprevenge
 * Date: 2016/11/9
 * Time: 14:08
 */
@Service
public class SenderEmailServiceImpl implements SenderEmailService {

    @Resource
    private SenderEmailMapper senderEmailMapper;

    @Resource
    private EmailSenderCache emailSenderCache;

    @Transactional
    @Override
    public SenderEmail addSender(String email, String password) {
        Preconditions.checkArgument(StringUtils.isNotBlank(email), "email must not be empty...");
        Preconditions.checkArgument(StringUtils.isNotBlank(password), "password must not be empty...");
        SenderEmail sender = new SenderEmail();
        sender.setEmail(email);
        sender.setPassword(password);
        senderEmailMapper.addSenderEmail(sender);
        //添加缓存中
        emailSenderCache.putEmail(sender);
        return sender;
    }

    @Transactional
    @Override
    public int updateSender(SenderEmail senderEmail) {
        Preconditions.checkNotNull(senderEmail);
        //缓存失效
        emailSenderCache.invalidSendEmail(senderEmail.getId());
        return senderEmailMapper.updateSenderEmail(senderEmail);
    }

    @Transactional
    @Override
    public int deleteSender(int id) {
        //缓存失效
        emailSenderCache.invalidSendEmail(id);
        return senderEmailMapper.deleteSendEmail(id);
    }

    @Override
    public List<SenderEmail> getSenderAll() {
        return senderEmailMapper.getSenderEmailAll();
    }

    @Override
    public SenderEmail getSenderById(Integer id) {
        if (emailSenderCache.get(id) == null) {
            SenderEmail senderEmail = senderEmailMapper.getSenderEmailById(id);
            if (senderEmail.getStatus() == 1) {
                emailSenderCache.putEmail(senderEmail);
            }
        }
        return emailSenderCache.get(id);
    }

    /**
     * 激活发送者邮箱
     *
     * @param id
     */
    @Override
    public void activeSenderEmail(Integer id) {
        senderEmailMapper.activeSenderEmail(id);
        SenderEmail senderEmail = senderEmailMapper.getSenderEmailById(id);
        emailSenderCache.putEmail(senderEmail);
    }

    @Override
    public List<SenderEmail> getActiveSenderAll() {
        return senderEmailMapper.getActiveSenderAll();
    }
}
