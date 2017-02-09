package com.alert.domain;

import java.util.Date;

/**
 * Project: AlertSystem
 * Module Desc:com.juntu.alert.domain
 * User: zjprevenge
 * Date: 2016/11/4
 * Time: 13:32
 */
public class SendRecord {
    private int id;
    private String sendEmail;
    private Date sendTime;
    private String sendContent;
    private String sendType;
    private String sendUser;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSendEmail() {
        return sendEmail;
    }

    public void setSendEmail(String sendEmail) {
        this.sendEmail = sendEmail;
    }

    public Date getSendTime() {
        return sendTime;
    }

    public void setSendTime(Date sendTime) {
        this.sendTime = sendTime;
    }

    public String getSendContent() {
        return sendContent;
    }

    public void setSendContent(String sendContent) {
        this.sendContent = sendContent;
    }

    public String getSendType() {
        return sendType;
    }

    public void setSendType(String sendType) {
        this.sendType = sendType;
    }

    public String getSendUser() {
        return sendUser;
    }

    public void setSendUser(String sendUser) {
        this.sendUser = sendUser;
    }
}
