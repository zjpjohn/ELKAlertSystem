package com.alert.service.impl;

import com.alert.domain.AlertFieldRule;
import com.alert.domain.AlertRateRule;
import com.alert.domain.BusinessLine;
import com.alert.mapper.BusinessLineMapper;
import com.alert.rule.FieldRule;
import com.alert.rule.FieldRuleCacheService;
import com.alert.rule.RateRule;
import com.alert.rule.RateRuleCacheService;
import com.alert.service.AlertRuleService;
import com.alert.service.BusinessLineService;
import com.google.common.base.Function;
import com.google.common.collect.Lists;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * Project: AlertSystem
 * Module Desc:com.juntu.alert.service.impl
 * User: zjprevenge
 * Date: 2016/12/5
 * Time: 9:57
 */
@Service
public class BusinessLineServiceImpl implements BusinessLineService {

    @Resource
    private BusinessLineMapper businessLineMapper;

    @Resource
    private AlertRuleService alertRuleService;

    @Resource
    private FieldRuleCacheService fieldRuleCacheService;

    @Resource
    private RateRuleCacheService rateRuleCacheService;

    @Override
    public BusinessLine getBusinessLineById(Integer id) {
        return businessLineMapper.getBusinessLineById(id);
    }

    @Override
    public BusinessLine getBusinessLineByName(String name) {
        return businessLineMapper.getBusinessLineByName(name);
    }

    @Override
    @Transactional
    public BusinessLine addBusinessLine(BusinessLine businessLine) {
        businessLineMapper.addBusinessLine(businessLine);
        return businessLine;
    }

    @Override
    public List<BusinessLine> getBusinessLineAll() {
        return businessLineMapper.getBusinessLineAll();
    }

    /**
     * 更新业务线，同时更新缓存
     *
     * @param businessLine
     * @return
     */
    @Override
    @Transactional
    public Integer updateBusinessLine(final BusinessLine businessLine) {
        BusinessLine line = businessLineMapper.getBusinessLineById(businessLine.getId());
        //清除field规则缓存
        fieldRuleCacheService.invalidate(line.getBusinessName());
        AlertFieldRule fieldRule = alertRuleService.getFieldRuleByBusiness(line.getId());
        List<AlertRateRule> rateRuleList = alertRuleService.getRateRuleByLine(line.getId());
        if (rateRuleList != null) {
            for (AlertRateRule rateRule : rateRuleList) {
                rateRuleCacheService.invalidate(rateRule.getBusinessName() + ":" + rateRule.getRateExpress());
            }
        }
        Integer result = businessLineMapper.updateBusinessLine(businessLine);
        if (result != null
                && result > 0
                && fieldRule != null) {
            //更新缓存
            FieldRule rule = new FieldRule();
            rule.setBusinessName(businessLine.getBusinessName());
            rule.setRuleName(fieldRule.getRuleName());
            rule.setFilters(Lists.newArrayList(fieldRule.getRuleValue().split(",")));
            fieldRuleCacheService.set(businessLine.getBusinessName(), rule);
            //更改rateRule缓存
            Lists.transform(rateRuleList, new Function<AlertRateRule, RateRule>() {
                @Override
                public RateRule apply(AlertRateRule alertRateRule) {
                    RateRule rateRule = new RateRule();
                    rateRule.setRateExpress(alertRateRule.getRateExpress());
                    rateRule.setRateThresh(alertRateRule.getRateThresh());
                    rateRule.setTimeInterval(alertRateRule.getInterval());
                    rateRule.setBusinessName(businessLine.getBusinessName());
                    rateRuleCacheService.set(businessLine.getBusinessName() + ":" + rateRule.getRateExpress(), rateRule);
                    return rateRule;
                }
            });
        }
        return result;
    }

    /**
     * 删除业务线，同时清除缓存
     *
     * @param id
     * @return
     */
    @Override
    @Transactional
    public Integer deleteBusinessLine(Integer id) {
        BusinessLine businessLine = businessLineMapper.getBusinessLineById(id);
        AlertFieldRule fieldRule = alertRuleService.getFieldRuleByBusiness(businessLine.getId());
        List<AlertRateRule> rateRuleList = alertRuleService.getRateRuleByLine(businessLine.getId());
        //删除field规则
        if (fieldRule != null) {
            alertRuleService.deleteFieldRule(fieldRule.getId());
        }
        //删除Rate规则
        if (rateRuleList != null && rateRuleList.size() > 0) {
            for (AlertRateRule rateRule : rateRuleList) {
                alertRuleService.deleteRateRule(rateRule.getId());
            }
        }
        //删除业务线
        return businessLineMapper.deleteBusinessLine(id);
    }

    /**
     * 激活业务线
     *
     * @param id
     */
    @Override
    @Transactional
    public void activeBusinessLine(Integer id) {
        BusinessLine businessLine = businessLineMapper.getBusinessLineById(id);
        AlertFieldRule fieldRule = alertRuleService.getFieldRuleByBusiness(businessLine.getId());
        List<AlertRateRule> rateRuleList = alertRuleService.getRateRuleByLine(businessLine.getId());
        //激活field规则
        if (fieldRule != null) {
            alertRuleService.activeFieldRule(fieldRule.getId());
        }
        //激活Rate规则
        if (rateRuleList != null && rateRuleList.size() > 0) {
            for (AlertRateRule rateRule : rateRuleList) {
                alertRuleService.activeRateRule(rateRule.getId());
            }
        }
        //激活业务线
        businessLineMapper.activeBusinessLine(id);
    }

    /**
     * 用户已经绑定的业务线
     *
     * @param userId 用户id
     * @return
     */
    @Override
    public List<BusinessLine> userBindBusinessLine(Integer userId) {
        return businessLineMapper.getBindBusinessLines(userId);
    }

    /**
     * 用户未绑定的业务线
     *
     * @param userId 用户id
     * @return
     */
    @Override
    public List<BusinessLine> userUnbindBusinessLine(Integer userId) {
        return businessLineMapper.getUnbindBusinessLines(userId);
    }
}
