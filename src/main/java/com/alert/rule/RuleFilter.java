package com.alert.rule;

/**
 * Project: AlertSystem
 * Module Desc:com.juntu.alert.rule
 * User: zjprevenge
 * Date: 2016/11/7
 * Time: 13:28
 */
public interface RuleFilter {

    boolean filter(Message message)throws Exception;
}
