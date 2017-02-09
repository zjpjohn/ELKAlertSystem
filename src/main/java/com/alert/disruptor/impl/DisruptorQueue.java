package com.alert.disruptor.impl;

import com.alert.disruptor.EventWorkHandle;
import com.alert.disruptor.MessageEvent;
import com.alert.email.EmailSenderService;
import com.alert.service.OperationLogService;
import com.google.common.base.Preconditions;
import com.alert.disruptor.Disruptor;
import com.alert.disruptor.EventFactory;
import com.alert.rule.Message;
import com.lmax.disruptor.RingBuffer;
import io.netty.util.concurrent.DefaultThreadFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;

/**
 * Project: AlertSystem
 * Module Desc:com.juntu.alert.disruptor.impl
 * User: zjprevenge
 * Date: 2016/11/9
 * Time: 11:01
 */
public class DisruptorQueue implements Disruptor, InitializingBean {

    private static final Logger log = LoggerFactory.getLogger(DisruptorQueue.class);

    private RingBuffer<MessageEvent> ringBuffer;

    private com.lmax.disruptor.dsl.Disruptor<MessageEvent> disruptor;

    private String threadName = "disruptorQueue";

    private int bufferSize;

    private int consumer;

    private EmailSenderService emailSenderService;

    private OperationLogService operationLogService;

    public int getBufferSize() {
        return bufferSize;
    }

    public void setBufferSize(int bufferSize) {
        this.bufferSize = bufferSize;
    }

    public int getConsumer() {
        return consumer;
    }

    public void setConsumer(int consumer) {
        this.consumer = consumer;
    }

    public String getThreadName() {
        return threadName;
    }

    public void setThreadName(String threadName) {
        this.threadName = threadName;
    }

    public void sendMessage(Message message) {
        emailSenderService.sendMessage(message);
    }

    public EmailSenderService getEmailSenderService() {
        return emailSenderService;
    }

    public void setEmailSenderService(EmailSenderService emailSenderService) {
        this.emailSenderService = emailSenderService;
    }

    /**
     * 向队列中添加消息
     *
     * @param message
     */
    @Override
    public void publish(Message message) {
        if (message != null) {
            if (log.isDebugEnabled()) {
                log.debug("add message to disruptor queue");
            }
            long next = ringBuffer.next();
            try {
                MessageEvent messageEvent = ringBuffer.get(next);
                messageEvent.setMessage(message);
            } finally {
                ringBuffer.publish(next);
            }
        }
    }

    /**
     * 停止queue
     */
    @Override
    public void shutDown() {
        disruptor.shutdown();
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        Preconditions.checkArgument(bufferSize > 0 && consumer > 0, "bufferSize,thread and consumer must great than zero... ");
        disruptor = new com.lmax.disruptor.dsl.Disruptor<MessageEvent>(new EventFactory(), bufferSize,new DefaultThreadFactory(threadName));
        EventWorkHandle[] workHandles = new EventWorkHandle[consumer];
        for (int i = 0; i < consumer; i++) {
            workHandles[i] = new EventWorkHandle(emailSenderService,operationLogService);
        }
        disruptor.handleEventsWithWorkerPool(workHandles);
        ringBuffer = disruptor.getRingBuffer();
        disruptor.start();
    }
}
