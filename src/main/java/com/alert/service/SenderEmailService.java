package com.alert.service;

import com.alert.domain.SenderEmail;

import java.util.List;

/**
 * Project: AlertSystem
 * Module Desc:com.juntu.alert.service
 * User: zjprevenge
 * Date: 2016/11/9
 * Time: 13:58
 */
public interface SenderEmailService {

    SenderEmail addSender(String email, String password);

    int updateSender(SenderEmail senderEmail);

    int deleteSender(int id);

    List<SenderEmail> getSenderAll();

    List<SenderEmail> getActiveSenderAll();

    SenderEmail getSenderById(Integer id);

    /**
     * 激活发送者邮箱
     *
     * @param id
     */
    void activeSenderEmail(Integer id);
}
