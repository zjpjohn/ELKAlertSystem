package com.alert.rule;

/**
 * Project: AlertSystem
 * Module Desc:com.juntu.alert.rule
 * User: zjprevenge
 * Date: 2016/11/7
 * Time: 9:36
 */
public enum RuleType {

    NGINX("nginx"), BUSINESS("business");

    private String name;

    RuleType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "RuleType{" +
                "name='" + name + '\'' +
                '}';
    }
}
