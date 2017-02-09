package com.alert.vo;

/**
 * Project: AlertSystem
 * Module Desc:com.juntu.alert.vo
 * User: zjprevenge
 * Date: 2016/12/6
 * Time: 15:08
 */
public class FieldRuleVo {
    private Integer id;
    private Integer businessId;
    private String businessName;
    private String ruleName;
    private String ruleValue;
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

    public String getRuleName() {
        return ruleName;
    }

    public void setRuleName(String ruleName) {
        this.ruleName = ruleName;
    }

    public String getRuleValue() {
        return ruleValue;
    }

    public void setRuleValue(String ruleValue) {
        this.ruleValue = ruleValue;
    }
}
