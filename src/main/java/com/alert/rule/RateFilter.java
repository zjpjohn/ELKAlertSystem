package com.alert.rule;

/**
 * Project: AlertSystem
 * Module Desc:com.juntu.alert.rule
 * User: zjprevenge
 * Date: 2016/12/2
 * Time: 10:32
 */
public interface RateFilter {

    /**
     * 时间频率过滤
     *
     * @param message   消息内容
     * @param exception 异常
     * @return
     */
    boolean filter(Message message, String exception);
}
