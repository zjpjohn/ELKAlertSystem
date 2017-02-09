package com.alert.mapper;

import com.alert.domain.AlertFieldRule;
import com.alert.domain.AlertRateRule;

import java.util.List;
import java.util.Map;

/**
 * Project: AlertSystem
 * Module Desc:com.juntu.alert.mapper
 * User: zjprevenge
 * Date: 2016/11/4
 * Time: 14:18
 */
public interface AlertRuleMapper {

    AlertFieldRule getFieldRuleById(Integer id);

    List<AlertFieldRule> getFieldRuleAll();

    AlertFieldRule getFieldRuleByName(String name);

    AlertFieldRule getFieldRuleByLine(Integer businessId);

    AlertRateRule getRateRuleById(Integer id);

    AlertRateRule getRateRuleByExpressLine(Map<String, String> params);

    List<AlertRateRule> getRateRuleByLine(Integer businessId);

    List<AlertRateRule> getRateRuleAll();

    Integer addFieldRule(AlertFieldRule fieldRule);

    Integer updateFieldRule(AlertFieldRule fieldRule);

    Integer addRateRule(AlertRateRule rateRule);

    void addRateRules(List<AlertRateRule> rateRules);

    Integer updateRateRule(AlertRateRule rateRule);

    Integer deleteFieldRule(Integer id);

    Integer deleteRateRule(Integer id);

    Integer activeFieldRule(Integer id);

    Integer activeRateRule(Integer id);
}
