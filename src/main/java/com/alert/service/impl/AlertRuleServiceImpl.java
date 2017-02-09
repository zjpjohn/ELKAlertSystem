package com.alert.service.impl;

import com.alert.domain.AlertFieldRule;
import com.alert.domain.AlertRateRule;
import com.alert.domain.BusinessLine;
import com.alert.rule.FieldRule;
import com.alert.rule.FieldRuleCacheService;
import com.alert.service.AlertRuleService;
import com.alert.vo.FieldRuleVo;
import com.alert.vo.RateRuleVo;
import com.google.common.base.Function;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.alert.mapper.AlertRuleMapper;
import com.alert.mapper.BusinessLineMapper;
import com.alert.rule.RateRule;
import com.alert.rule.RateRuleCacheService;
import com.alert.vo.RuleVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * Project: AlertSystem
 * Module Desc:com.juntu.alert.service.impl
 * User: zjprevenge
 * Date: 2016/11/9
 * Time: 17:07
 */
@Service
public class AlertRuleServiceImpl implements AlertRuleService {

    private static final Logger log = LoggerFactory.getLogger(AlertRuleServiceImpl.class);

    @Resource
    private AlertRuleMapper alertRuleMapper;

    @Resource
    private FieldRuleCacheService fieldRuleCacheService;

    @Resource
    private BusinessLineMapper businessLineMapper;

    @Resource
    private RateRuleCacheService rateRuleCacheService;

    @Override
    public AlertFieldRule getFieldRuleById(Integer id) {
        return alertRuleMapper.getFieldRuleById(id);
    }

    @Override
    public AlertFieldRule getFieldRuleByName(String name) {
        return alertRuleMapper.getFieldRuleByName(name);
    }

    @Override
    public AlertFieldRule getFieldRuleByBusiness(Integer businessId) {
        AlertFieldRule fieldRule = alertRuleMapper.getFieldRuleByLine(businessId);
        if (fieldRule != null
                && fieldRuleCacheService.get(fieldRule.getBusinessName()) == null) {
            FieldRule rule = new FieldRule();
            rule.setRuleName(fieldRule.getRuleName());
            rule.setBusinessName(fieldRule.getBusinessName());
            List<String> filters = Lists.newArrayList(fieldRule.getRuleValue().split(","));
            rule.setFilters(filters);
            fieldRuleCacheService.set(fieldRule.getBusinessName(), rule);
        }
        return fieldRule;
    }

    @Override
    public AlertRateRule getRateRuleById(Integer id) {
        return alertRuleMapper.getRateRuleById(id);
    }

    @Override
    public AlertRateRule getRateRuleByExceptionBusiness(Map<String, String> params) {
        AlertRateRule rateRule = alertRuleMapper.getRateRuleByExpressLine(params);
        String key = params.get("business") + ":" + params.get("rateExpress");
        if (rateRuleCacheService.get(key) == null) {
            RateRule rule = new RateRule();
            rule.setBusinessName(rateRule.getBusinessName());
            rule.setRateExpress(rateRule.getRateExpress());
            rule.setTimeInterval(rateRule.getInterval());
            rule.setRateThresh(rateRule.getRateThresh());
            rateRuleCacheService.set(key, rule);
        }
        return rateRule;
    }

    @Override
    public List<AlertRateRule> getRateRuleByLine(Integer businessId) {
        return alertRuleMapper.getRateRuleByLine(businessId);
    }

    /**
     * 添加字段规则
     * 如果字段规则存在，则进行追加更新
     *
     * @param fieldRule
     * @return
     */
    @Override
    @Transactional
    public AlertFieldRule addFieldRule(AlertFieldRule fieldRule) {
        AlertFieldRule ruleByLine = alertRuleMapper.getFieldRuleByName(fieldRule.getRuleName());
        if (ruleByLine == null) {
            alertRuleMapper.addFieldRule(fieldRule);
        } else {
            String ruleValue = ruleByLine.getRuleValue();
            ruleValue = new StringBuffer(ruleValue).append(",").append(fieldRule.getRuleValue()).toString();
            ruleByLine.setRuleValue(ruleValue);
            fieldRule.setId(ruleByLine.getId());
            alertRuleMapper.updateFieldRule(ruleByLine);
        }
        BusinessLine businessLine = businessLineMapper.getBusinessLineById(fieldRule.getBusinessId());
        fieldRule.setBusinessName(businessLine.getBusinessName());
        FieldRule rule = new FieldRule();
        rule.setRuleName(fieldRule.getRuleName());
        rule.setBusinessName(businessLine.getBusinessName());
        List<String> filters = Lists.newArrayList(fieldRule.getRuleValue().split(","));
        rule.setFilters(filters);
        fieldRuleCacheService.set(businessLine.getBusinessName(), rule);
        return fieldRule;
    }

    @Override
    @Transactional
    public void updateFieldRule(AlertFieldRule fieldRule) {
        alertRuleMapper.updateFieldRule(fieldRule);
        AlertFieldRule alertFieldRule = alertRuleMapper.getFieldRuleById(fieldRule.getId());
        FieldRule rule = new FieldRule();
        rule.setRuleName(alertFieldRule.getRuleName());
        rule.setBusinessName(alertFieldRule.getBusinessName());
        List<String> filters = Lists.newArrayList(alertFieldRule.getRuleValue().split(","));
        rule.setFilters(filters);
        fieldRuleCacheService.set(alertFieldRule.getBusinessName(), rule);
    }

    @Override
    @Transactional
    public AlertRateRule addRateRule(AlertRateRule rateRule) {
        alertRuleMapper.addRateRule(rateRule);
        BusinessLine businessLine = businessLineMapper.getBusinessLineById(rateRule.getBusinessId());
        RateRule rule = new RateRule();
        rule.setBusinessName(businessLine.getBusinessName());
        rule.setRateThresh(rateRule.getRateThresh());
        rule.setRateExpress(rateRule.getRateExpress());
        rule.setTimeInterval(rateRule.getInterval());
        rateRuleCacheService.set(rule.getBusinessName() + ":" + rule.getRateExpress(), rule);
        return rateRule;

    }

    @Override
    @Transactional
    public void updateRateRule(AlertRateRule rateRule) {
        alertRuleMapper.updateRateRule(rateRule);
        AlertRateRule alertRateRule = alertRuleMapper.getRateRuleById(rateRule.getId());
        RateRule rule = new RateRule();
        rule.setRateExpress(alertRateRule.getRateExpress());
        rule.setTimeInterval(alertRateRule.getInterval());
        rule.setRateThresh(alertRateRule.getRateThresh());
        rule.setBusinessName(alertRateRule.getBusinessName());
        rateRuleCacheService.set(alertRateRule.getBusinessName() + ":" + alertRateRule.getRateExpress(), rule);
    }

    @Override
    @Transactional
    public void deleteFieldRule(Integer id) {
        AlertFieldRule fieldRule = alertRuleMapper.getFieldRuleById(id);
        if (fieldRule != null) {
            //清除缓存
            fieldRuleCacheService.invalidate(fieldRule.getBusinessName());
            alertRuleMapper.deleteFieldRule(id);
        }
    }

    @Override
    @Transactional
    public void deleteRateRule(Integer id) {
        AlertRateRule rateRule = alertRuleMapper.getRateRuleById(id);
        if (rateRule != null) {
            //清除缓存
            rateRuleCacheService.invalidate(rateRule.getBusinessName() + ":" + rateRule.getRateExpress());
            alertRuleMapper.deleteRateRule(id);
        }
    }

    @Override
    @Transactional
    public void addRule(RuleVo ruleVo) {
        FieldRuleVo fieldRuleVo = ruleVo.getFieldRuleVo();
        List<RateRuleVo> rateRuleVos = ruleVo.getRateRuleVo();
        //构建AlertFieldRule
        AlertFieldRule alertFieldRule = new AlertFieldRule();
        alertFieldRule.setBusinessName(fieldRuleVo.getBusinessName());
        alertFieldRule.setRuleName(fieldRuleVo.getRuleName());
        alertFieldRule.setRuleValue(fieldRuleVo.getRuleValue());
        addFieldRule(alertFieldRule);
        //构建AlertRateRule
        List<AlertRateRule> alertRateRules = Lists.transform(rateRuleVos, new Function<RateRuleVo, AlertRateRule>() {
            @Override
            public AlertRateRule apply(RateRuleVo rateRuleVo) {
                AlertRateRule rateRule = new AlertRateRule();
                rateRule.setBusinessName(rateRuleVo.getBusinessName());
                rateRule.setRateThresh(rateRuleVo.getRateThresh());
                rateRule.setInterval(rateRuleVo.getInterval());
                rateRule.setRateExpress(rateRuleVo.getRateExpress());
                return rateRule;
            }
        });
        alertRuleMapper.addRateRules(alertRateRules);
    }

    @Override
    public RuleVo queryRule(final Integer businessId) {
        RuleVo ruleVo = new RuleVo();
        AlertFieldRule fieldRuleByLine = alertRuleMapper.getFieldRuleByLine(businessId);
        final String businessName = fieldRuleByLine.getBusinessName();
        System.out.println(businessName);
        List<AlertRateRule> alertRateRules = alertRuleMapper.getRateRuleByLine(businessId);
        FieldRuleVo fieldRuleVo = null;
        if (fieldRuleByLine != null) {
            fieldRuleVo = new FieldRuleVo();
            fieldRuleVo.setBusinessName(businessName);
            fieldRuleVo.setRuleValue(fieldRuleByLine.getRuleValue());
            fieldRuleVo.setRuleName(fieldRuleByLine.getRuleName());
        }
        ruleVo.setBusinessName(businessName);
        ruleVo.setFieldRuleVo(fieldRuleVo);
        List<RateRuleVo> rateRuleVos = null;
        if (alertRateRules != null) {
            rateRuleVos = Lists.transform(alertRateRules, new Function<AlertRateRule, RateRuleVo>() {
                @Override
                public RateRuleVo apply(AlertRateRule alertRateRule) {
                    RateRuleVo rateRuleVo = new RateRuleVo();
                    rateRuleVo.setBusinessName(businessName);
                    rateRuleVo.setRateThresh(alertRateRule.getRateThresh());
                    rateRuleVo.setRateExpress(alertRateRule.getRateExpress());
                    rateRuleVo.setInterval(alertRateRule.getInterval());
                    return rateRuleVo;
                }
            });
        }
        ruleVo.setRateRuleVo(rateRuleVos);
        return ruleVo;
    }

    @Override
    public List<RuleVo> queryRules() {
        List<AlertFieldRule> fieldRules = alertRuleMapper.getFieldRuleAll();
        List<RuleVo> ruleVos = Lists.newArrayList();
        for (AlertFieldRule fieldRule : fieldRules) {
            RuleVo ruleVo = new RuleVo();
            FieldRuleVo fieldRuleVo = new FieldRuleVo();
            fieldRuleVo.setBusinessId(fieldRule.getBusinessId());
            fieldRuleVo.setRuleName(fieldRule.getRuleName());
            fieldRuleVo.setRuleValue(fieldRule.getRuleValue());
            fieldRuleVo.setBusinessName(fieldRule.getBusinessName());
            ruleVo.setBusinessName(fieldRule.getBusinessName());
            ruleVo.setFieldRuleVo(fieldRuleVo);
            List<AlertRateRule> rateRules = alertRuleMapper.getRateRuleByLine(fieldRule.getBusinessId());
            List<RateRuleVo> rateRuleVos = Lists.newArrayList();
            for (AlertRateRule rateRule : rateRules) {
                RateRuleVo rateRuleVo = new RateRuleVo();
                rateRuleVo.setBusinessId(rateRule.getBusinessId());
                rateRuleVo.setBusinessName(rateRule.getBusinessName());
                rateRuleVo.setInterval(rateRule.getInterval());
                rateRuleVo.setRateExpress(rateRule.getRateExpress());
                rateRuleVo.setRateThresh(rateRule.getRateThresh());
                rateRuleVos.add(rateRuleVo);
            }
            ruleVo.setRateRuleVo(rateRuleVos);
            ruleVos.add(ruleVo);
        }
        return ruleVos;
    }

    //加载数据至缓存中
    @Override
    public void loadRule() {
        //清空缓存
        fieldRuleCacheService.invalidateAll();
        //加载FieldRule数据至缓存
        List<AlertFieldRule> fieldRuleList = alertRuleMapper.getFieldRuleAll();
        if (fieldRuleList != null && fieldRuleList.size() > 0) {
            Map<String, FieldRule> fieldRuleMap = Maps.newHashMap();
            for (AlertFieldRule fieldRule : fieldRuleList) {
                FieldRule rule = new FieldRule();
                rule.setRuleName(fieldRule.getRuleName());
                rule.setBusinessName(fieldRule.getBusinessName());
                List<String> filters = Lists.newArrayList(fieldRule.getRuleValue().split(","));
                rule.setFilters(filters);
                fieldRuleMap.put(fieldRule.getBusinessName(), rule);
            }
            fieldRuleCacheService.putAll(fieldRuleMap);
        }
        //清空缓存
        rateRuleCacheService.invalidateAll();
        //加载RateRule数据至缓存
        List<AlertRateRule> rateRuleList = alertRuleMapper.getRateRuleAll();
        if (rateRuleList != null && rateRuleList.size() > 0) {
            Map<String, RateRule> rateRuleMap = Maps.newHashMap();
            for (AlertRateRule rateRule : rateRuleList) {
                RateRule rule = new RateRule();
                rule.setRateExpress(rateRule.getRateExpress());
                rule.setTimeInterval(rateRule.getInterval());
                rule.setRateThresh(rateRule.getRateThresh());
                rule.setBusinessName(rateRule.getBusinessName());
                rateRuleMap.put(rateRule.getBusinessName() + ":" + rateRule.getRateExpress(), rule);
            }
            rateRuleCacheService.putAll(rateRuleMap);
        }
    }

    /**
     * 激活规则并添加置缓存中
     *
     * @param id
     */
    @Override
    public void activeFieldRule(Integer id) {
        alertRuleMapper.activeFieldRule(id);
        AlertFieldRule fieldRule = alertRuleMapper.getFieldRuleById(id);
        AlertFieldRule alertFieldRule = alertRuleMapper.getFieldRuleById(fieldRule.getId());
        FieldRule rule = new FieldRule();
        rule.setRuleName(alertFieldRule.getRuleName());
        rule.setBusinessName(alertFieldRule.getBusinessName());
        List<String> filters = Lists.newArrayList(alertFieldRule.getRuleValue().split(","));
        rule.setFilters(filters);
        fieldRuleCacheService.set(alertFieldRule.getBusinessName(), rule);
    }

    /**
     * 激活规则并添加至缓存中
     *
     * @param id
     */
    @Override
    public void activeRateRule(Integer id) {
        alertRuleMapper.activeRateRule(id);
        AlertRateRule rateRule = alertRuleMapper.getRateRuleById(id);
        AlertRateRule alertRateRule = alertRuleMapper.getRateRuleById(rateRule.getId());
        RateRule rule = new RateRule();
        rule.setRateExpress(alertRateRule.getRateExpress());
        rule.setTimeInterval(alertRateRule.getInterval());
        rule.setRateThresh(alertRateRule.getRateThresh());
        rule.setBusinessName(alertRateRule.getBusinessName());
        rateRuleCacheService.set(alertRateRule.getBusinessName() + ":" + alertRateRule.getRateExpress(), rule);
    }
}
