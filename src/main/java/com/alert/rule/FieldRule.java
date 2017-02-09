package com.alert.rule;

import java.util.List;
import java.util.Map;

/**
 * Project: AlertSystem
 * Module Desc:com.juntu.alert.rule
 * User: zjprevenge
 * Date: 2016/11/7
 * Time: 9:17
 */
public class FieldRule {

    //规则名称
    private String ruleName;
    //规则描述
    private String description;
    //业务线名称
    private String businessName;
    /**
     * 过滤字段,对字段中包含的值进行报警
     * 多个值用分号隔开
     */
    private List<String> filters;

    /**
     * 附加条件
     * todo
     */
    private Map<String, String> attachCon;

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<String> getFilters() {
        return filters;
    }

    public void setFilters(List<String> filters) {
        this.filters = filters;
    }

    public Map<String, String> getAttachCon() {
        return attachCon;
    }

    public void setAttachCon(Map<String, String> attachCon) {
        this.attachCon = attachCon;
    }

}
