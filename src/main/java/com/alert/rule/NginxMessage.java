package com.alert.rule;

/**
 * Project: AlertSystem
 * Module Desc:com.juntu.alert.rule
 * User: zjprevenge
 * Date: 2016/11/7
 * Time: 9:48
 */
public class NginxMessage extends Message {

    private String request;
    private String request_method;
    private String remote_user;
    private double request_time;
    private String timestamp;
    private String request_body;

    public String getRequest() {
        return request;
    }

    public void setRequest(String request) {
        this.request = request;
    }

    public String getRequest_method() {
        return request_method;
    }

    public void setRequest_method(String request_method) {
        this.request_method = request_method;
    }

    public String getRemote_user() {
        return remote_user;
    }

    public void setRemote_user(String remote_user) {
        this.remote_user = remote_user;
    }

    public double getRequest_time() {
        return request_time;
    }

    public void setRequest_time(double request_time) {
        this.request_time = request_time;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getRequest_body() {
        return request_body;
    }

    public void setRequest_body(String request_body) {
        this.request_body = request_body;
    }

    @Override
    public String toString() {
        return "NginxMessage{" +
                "request='" + request + '\'' +
                ", request_method='" + request_method + '\'' +
                ", remote_user='" + remote_user + '\'' +
                ", request_time=" + request_time +
                ", timestamp='" + timestamp + '\'' +
                ", request_body='" + request_body + '\'' +
                '}';
    }
}
