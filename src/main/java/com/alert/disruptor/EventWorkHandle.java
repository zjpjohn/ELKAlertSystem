package com.alert.disruptor;

import com.alert.email.EmailSenderService;
import com.alert.service.OperationLogService;
import com.alert.rule.Message;
import com.lmax.disruptor.WorkHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Project: AlertSystem
 * Module Desc:com.juntu.alert.disruptor
 * User: zjprevenge
 * Date: 2016/11/9
 * Time: 11:00
 */
public class EventWorkHandle implements WorkHandler<MessageEvent> {

    private static final Logger log= LoggerFactory.getLogger(EventWorkHandle.class);

    private EmailSenderService emailSenderService;

    private OperationLogService operationLogService;

    public EventWorkHandle(EmailSenderService emailSenderService, OperationLogService operationLogService) {
        this.emailSenderService = emailSenderService;
        this.operationLogService = operationLogService;
    }

    @Override
    public void onEvent(MessageEvent messageEvent) throws Exception {
        Message message = messageEvent.getMessage();
        emailSenderService.sendMessage(message);
    }
}
