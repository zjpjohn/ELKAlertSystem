package com.alert.domain;

import java.util.Date;

/**
 * Project: AlertSystem
 * Module Desc:com.juntu.alert.domain
 * User: zjprevenge
 * Date: 2016/12/1
 * Time: 17:02
 * 时间频率过滤
 */
public class AlertRateRule {

    private Integer id;
    //业务线id
    private Integer businessId;
    //异常所属业务线
    private String businessName;
    private Integer rateThresh;
    private Integer interval;
    //异常名称
    private String rateExpress;
    private Date createTime;
    private Date updateTime;
    private int active;

    public Integer getBusinessId() {
        return businessId;
    }

    public void setBusinessId(Integer businessId) {
        this.businessId = businessId;
    }

    public Integer getInterval() {
        return interval;
    }

    public void setInterval(Integer interval) {
        this.interval = interval;
    }

    public String getBusinessName() {
        return businessName;
    }

    public void setBusinessName(String businessName) {
        this.businessName = businessName;
    }

    public String getRateExpress() {
        return rateExpress;
    }

    public void setRateExpress(String rateExpress) {
        this.rateExpress = rateExpress;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getRateThresh() {
        return rateThresh;
    }

    public void setRateThresh(Integer rateThresh) {
        this.rateThresh = rateThresh;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public int getActive() {
        return active;
    }

    public void setActive(int active) {
        this.active = active;
    }
}
