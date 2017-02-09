package com.alert.config;

import com.alert.disruptor.impl.DisruptorQueue;
import com.alert.email.EmailSenderService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;

/**
 * Project: AlertSystem
 * Module Desc:com.juntu.alert.config
 * User: zjprevenge
 * Date: 2016/11/9
 * Time: 12:03
 */
@Configuration
public class DisruptorQueueConfig {

    @Resource
    private EmailSenderService emailSenderService;

    @Bean(destroyMethod = "shutDown")
    public DisruptorQueue disruptorQueue() {
        DisruptorQueue queue = new DisruptorQueue();
        queue.setBufferSize(1024);
        queue.setConsumer(Runtime.getRuntime().availableProcessors());
        queue.setThreadName("disruptorQueue");
        queue.setEmailSenderService(emailSenderService);
        return queue;
    }
}
