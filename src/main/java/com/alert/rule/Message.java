package com.alert.rule;

/**
 * Project: AlertSystem
 * Module Desc:com.juntu.alert.rule
 * User: zjprevenge
 * Date: 2016/11/7
 * Time: 9:40
 */
public abstract class Message {
    //消息来源业务线
    protected String type;
    //消息粗粒度标志（nginx,business）
    protected String tag;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }
}
