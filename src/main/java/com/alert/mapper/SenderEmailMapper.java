package com.alert.mapper;

import com.alert.domain.SenderEmail;

import java.util.List;

/**
 * Project: AlertSystem
 * Module Desc:com.juntu.alert.mapper
 * User: zjprevenge
 * Date: 2016/11/4
 * Time: 14:20
 */
public interface SenderEmailMapper {

    int addSenderEmail(SenderEmail senderEmail);

    int updateSenderEmail(SenderEmail senderEmail);

    int deleteSendEmail(Integer id);

    Integer activeSenderEmail(Integer id);

    List<SenderEmail> getSenderEmailAll();

    SenderEmail getSenderEmailById(Integer id);

    List<SenderEmail> getActiveSenderAll();
}
