package com.alert.disruptor;

import com.alert.rule.Message;

/**
 * Project: AlertSystem
 * Module Desc:com.juntu.alert.disruptor
 * User: zjprevenge
 * Date: 2016/11/9
 * Time: 9:39
 */
public interface Disruptor {

    /**
     * 向队列中添加消息
     *
     * @param message
     */
    void publish(Message message);

    /**
     * 停止queue
     */
    void shutDown();

}
