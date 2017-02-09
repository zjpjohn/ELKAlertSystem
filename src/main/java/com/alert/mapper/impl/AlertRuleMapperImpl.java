package com.alert.mapper.impl;

import com.alert.domain.AlertFieldRule;
import com.alert.domain.AlertRateRule;
import com.alert.mapper.AlertRuleMapper;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * Project: system
 * Module Desc:com.juntu.alert.mapper.impl
 * User: zjprevenge
 * Date: 2016/12/29
 * Time: 9:15
 */
@Repository
public class AlertRuleMapperImpl implements AlertRuleMapper {

    @Resource
    private SqlSession sqlSession;

    @Override
    public AlertFieldRule getFieldRuleById(Integer id) {
        return sqlSession.selectOne("AlertRuleMapper.getFieldRuleById", id);
    }

    @Override
    public List<AlertFieldRule> getFieldRuleAll() {
        return sqlSession.selectList("AlertRuleMapper.getFieldRuleAll");
    }

    @Override
    public AlertFieldRule getFieldRuleByName(String name) {
        return sqlSession.selectOne("AlertRuleMapper.getFieldRuleByName", name);
    }

    @Override
    public AlertFieldRule getFieldRuleByLine(Integer businessId) {
        return sqlSession.selectOne("AlertRuleMapper.getFieldRuleByLine", businessId);
    }

    @Override
    public AlertRateRule getRateRuleById(Integer id) {
        return sqlSession.selectOne("AlertRuleMapper.getRateRuleById", id);
    }

    @Override
    public AlertRateRule getRateRuleByExpressLine(Map<String, String> params) {
        return sqlSession.selectOne("AlertRuleMapper.getRateRuleByExpressLine", params);
    }

    @Override
    public List<AlertRateRule> getRateRuleByLine(Integer businessId) {
        return sqlSession.selectList("AlertRuleMapper.getRateRuleByLine", businessId);
    }

    @Override
    public List<AlertRateRule> getRateRuleAll() {
        return sqlSession.selectList("AlertRuleMapper.getRateRuleAll");
    }

    @Override
    public Integer addFieldRule(AlertFieldRule fieldRule) {
        return sqlSession.insert("AlertRuleMapper.addFieldRule", fieldRule);
    }

    @Override
    public Integer updateFieldRule(AlertFieldRule fieldRule) {
        return sqlSession.update("AlertRuleMapper.updateFieldRule", fieldRule);
    }

    @Override
    public Integer addRateRule(AlertRateRule rateRule) {
        return sqlSession.insert("AlertRuleMapper.addRateRule", rateRule);
    }

    @Override
    public void addRateRules(List<AlertRateRule> rateRules) {
        sqlSession.insert("AlertRuleMapper.addRateRules", rateRules);
    }

    @Override
    public Integer updateRateRule(AlertRateRule rateRule) {
        return sqlSession.update("AlertRuleMapper.updateRateRule",rateRule);
    }

    @Override
    public Integer deleteFieldRule(Integer id) {
        return sqlSession.update("AlertRuleMapper.deleteFieldRule",id);
    }

    @Override
    public Integer deleteRateRule(Integer id) {
        return sqlSession.update("AlertRuleMapper.deleteRateRule",id);
    }

    @Override
    public Integer activeFieldRule(Integer id) {
        return sqlSession.update("AlertRuleMapper.activeFieldRule",id);
    }

    @Override
    public Integer activeRateRule(Integer id) {
        return sqlSession.update("AlertRuleMapper.activeRateRule",id);
    }
}
