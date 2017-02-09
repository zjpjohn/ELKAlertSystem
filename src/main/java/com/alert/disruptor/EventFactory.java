package com.alert.disruptor;

/**
 * Project: AlertSystem
 * Module Desc:com.juntu.alert.disruptor
 * User: zjprevenge
 * Date: 2016/11/9
 * Time: 10:57
 */
public class EventFactory implements com.lmax.disruptor.EventFactory<MessageEvent> {

    @Override
    public MessageEvent newInstance() {
        return new MessageEvent();
    }
}
