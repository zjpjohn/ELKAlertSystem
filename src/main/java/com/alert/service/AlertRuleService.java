package com.alert.service;


import com.alert.domain.AlertFieldRule;
import com.alert.domain.AlertRateRule;
import com.alert.vo.RuleVo;

import java.util.List;
import java.util.Map;

/**
 * Project: AlertSystem
 * Module Desc:com.juntu.alert.service
 * User: zjprevenge
 * Date: 2016/11/9
 * Time: 17:06
 */
public interface AlertRuleService {

    /**
     * 根据id获取fieldRule
     *
     * @param id
     * @return
     */
    AlertFieldRule getFieldRuleById(Integer id);

    /**
     * 根据name获取fieldRule
     *
     * @param name
     * @return
     */
    AlertFieldRule getFieldRuleByName(String name);

    /**
     * 获取业务线对应的字段过滤规则
     *
     * @param businessId
     * @return
     */
    AlertFieldRule getFieldRuleByBusiness(Integer businessId);

    AlertRateRule getRateRuleById(Integer id);

    /**
     * 根据异常名称和业务线获取异常对应的
     *
     * @param params
     * @return
     */
    AlertRateRule getRateRuleByExceptionBusiness(Map<String, String> params);

    /**
     * 获取业务线下的规则
     *
     * @param businessId
     * @return
     */
    List<AlertRateRule> getRateRuleByLine(Integer businessId);

    /**
     * 添加fieldRule
     *
     * @param fieldRule
     * @return
     */
    AlertFieldRule addFieldRule(AlertFieldRule fieldRule);

    /**
     * 更新fieldRule
     *
     * @param fieldRule
     */
    void updateFieldRule(AlertFieldRule fieldRule);

    /**
     * 添加RateRule
     *
     * @param rateRule
     * @return
     */
    AlertRateRule addRateRule(AlertRateRule rateRule);

    /**
     * 更新RateRule
     *
     * @param rateRule
     * @return
     */
    void updateRateRule(AlertRateRule rateRule);

    /**
     * 删除fieldrule
     *
     * @param id
     */
    void deleteFieldRule(Integer id);

    /**
     * 删除raterule
     *
     * @param id
     */
    void deleteRateRule(Integer id);

    void activeFieldRule(Integer id);

    void activeRateRule(Integer id);

    void addRule(RuleVo ruleVo);

    RuleVo queryRule(Integer businessId);

    List<RuleVo> queryRules();

    void loadRule();
}
