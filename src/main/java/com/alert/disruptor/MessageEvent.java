package com.alert.disruptor;

import com.alert.rule.Message;

/**
 * Project: AlertSystem
 * Module Desc:com.juntu.alert.disruptor
 * User: zjprevenge
 * Date: 2016/11/9
 * Time: 10:56
 */
public class MessageEvent {

    private Message message;

    public Message getMessage() {
        return message;
    }

    public void setMessage(Message message) {
        this.message = message;
    }
}
