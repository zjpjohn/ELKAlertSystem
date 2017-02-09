package com.alert.rule;

/**
 * Project: AlertSystem
 * Module Desc:com.juntu.alert.rule
 * User: zjprevenge
 * Date: 2016/12/2
 * Time: 11:45
 */
public class RateRule {
    //所属业务线
    private String businessName;
    //计算频率表达式
    private String rateExpress;
    //频率阈值
    private Integer rateThresh;
    //时间间隔
    private int timeInterval;

    public int getTimeInterval() {
        return timeInterval;
    }

    public void setTimeInterval(int timeInterval) {
        this.timeInterval = timeInterval;
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
}
