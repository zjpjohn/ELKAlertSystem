package com.alert.vo;

/**
 * Project: AlertSystem
 * Module Desc:com.juntu.alert.vo
 * User: zjprevenge
 * Date: 2016/12/6
 * Time: 15:09
 */
public class RateRuleVo {
    private Integer id;
    private Integer businessId;
    private String businessName;
    private String rateExpress;
    private Integer rateThresh;
    private Integer interval;
    private Integer active;

    public Integer getActive() {
        return active;
    }

    public void setActive(Integer active) {
        this.active = active;
    }

    public Integer getBusinessId() {
        return businessId;
    }

    public void setBusinessId(Integer businessId) {
        this.businessId = businessId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public Integer getRateThresh() {
        return rateThresh;
    }

    public void setRateThresh(Integer rateThresh) {
        this.rateThresh = rateThresh;
    }

    public Integer getInterval() {
        return interval;
    }

    public void setInterval(Integer interval) {
        this.interval = interval;
    }
}
