package com.alert.vo;


import java.util.List;

/**
 * Project: AlertSystem
 * Module Desc:com.juntu.alert.vo
 * User: zjprevenge
 * Date: 2016/12/6
 * Time: 14:48
 */
public class RuleVo {
    private String businessName;
    private FieldRuleVo fieldRuleVo;
    private List<RateRuleVo> rateRuleVo;

    public String getBusinessName() {
        return businessName;
    }

    public void setBusinessName(String businessName) {
        this.businessName = businessName;
    }

    public FieldRuleVo getFieldRuleVo() {
        return fieldRuleVo;
    }

    public void setFieldRuleVo(FieldRuleVo fieldRuleVo) {
        this.fieldRuleVo = fieldRuleVo;
    }

    public List<RateRuleVo> getRateRuleVo() {
        return rateRuleVo;
    }

    public void setRateRuleVo(List<RateRuleVo> rateRuleVo) {
        this.rateRuleVo = rateRuleVo;
    }
}
