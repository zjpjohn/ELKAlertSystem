package com.alert.kafka;

import com.alert.rule.*;
import com.google.common.base.Optional;
import com.alert.disruptor.impl.DisruptorQueue;
import com.alert.utils.JsonUtils;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * Project: system
 * Module Desc:com.juntu.alert.spring.kafka
 * User: zjprevenge
 * Date: 2016/12/27
 * Time: 17:08
 */
@Component
public class MessageListener {

    private static final Logger log = LoggerFactory.getLogger(MessageListener.class);

    @Resource
    private AlertRuleFilter filter;

    @Resource
    private DisruptorQueue queue;

    @KafkaListener(topics = "logstash.alert", group = "group-alert")
    public void listen(ConsumerRecord<String, String> record) {
        Optional<String> optional = Optional.fromNullable(record.value());
        if (optional.isPresent()) {
            String msg = optional.get();
            Message message = null;
            try {
                String tag = JsonUtils.getJsonFieldValue(msg, "tag");
                if (RuleType.NGINX.getName().equals(tag)) {
                    message = JsonUtils.json2Bean(msg, NginxMessage.class);
                } else if (RuleType.BUSINESS.getName().equals(tag)) {
                    message = JsonUtils.json2Bean(msg, BusinessMessage.class);
                }
                if (message != null) {
                    message = filter.filterMessage(message);
                }
                if (message != null) {
                    queue.publish(message);
                }
            } catch (Exception e) {
                log.error("handle message error：{}", e);
            }
        }
    }
}
