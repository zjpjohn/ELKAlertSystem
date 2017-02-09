package com.alert.mapper.impl;

import com.alert.domain.EmailTemplate;
import com.alert.mapper.EmailTemplateMapper;
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
 * Time: 9:18
 */
@Repository
public class EmailTemplateMapperImpl implements EmailTemplateMapper {

    @Resource
    private SqlSession sqlSession;

    @Override
    public Integer addEmailTemplate(EmailTemplate emailTemplate) {
        return sqlSession.insert("EmailTemplateMapper.addEmailTemplate", emailTemplate);
    }

    @Override
    public int updateEmailTemplate(EmailTemplate emailTemplate) {
        return sqlSession.update("EmailTemplateMapper.updateEmailTemplate", emailTemplate);
    }

    @Override
    public int activeTemplate(Integer id) {
        return sqlSession.update("EmailTemplateMapper.activeTemplate", id);
    }

    @Override
    public void deleteEmailTemplate(Integer templateId) {
        sqlSession.update("EmailTemplateMapper.deleteEmailTemplate", templateId);
    }

    @Override
    public void activeEmailTemplate(Map<String, Integer> params) {
        sqlSession.update("EmailTemplateMapper.activeEmailTemplate", params);
    }

    @Override
    public void addAndActiveEmailTemplate(Map<String, Integer> params) {
        sqlSession.update("EmailTemplateMapper.addAndActiveEmailTemplate", params);
    }

    @Override
    public int addMessageEmailTemplate(Map<String, Integer> params) {
        return sqlSession.insert("EmailTemplateMapper.addMessageEmailTemplate",params);
    }

    @Override
    public EmailTemplate getEmailTemplateById(Integer id) {
        return sqlSession.selectOne("EmailTemplateMapper.getEmailTemplateById",id);
    }

    @Override
    public EmailTemplate getEmailTemplateByName(String templateName) {
        return sqlSession.selectOne("EmailTemplateMapper.getEmailTemplateByName",templateName);
    }

    @Override
    public EmailTemplate getActiveTemplateByTag(String businessName) {
        return sqlSession.selectOne("EmailTemplateMapper.getActiveTemplateByTag",businessName);
    }

    @Override
    public List<EmailTemplate> getTemplateByBusinessName(String businessName) {
        return sqlSession.selectList("EmailTemplateMapper.getTemplateByBusinessName", businessName);
    }

    @Override
    public List<EmailTemplate> getEmailTemplateAll() {
        return sqlSession.selectList("EmailTemplateMapper.getEmailTemplateAll");
    }

    @Override
    public int unbindingTemplate(Map<String, Object> params) {
        return sqlSession.update("EmailTemplateMapper.unbindingTemplate",params);
    }

    @Override
    public EmailTemplate getActiveTemplateByBusinessId(Map<String, Object> params) {
        return sqlSession.selectOne("EmailTemplateMapper.getActiveTemplateByBusinessId",params);
    }

    @Override
    public List<EmailTemplate> getBindTemplate(Integer businessId) {
        return sqlSession.selectList("EmailTemplateMapper.getBindTemplate",businessId);
    }

    @Override
    public List<EmailTemplate> getUnbindTemplate(Integer businessId) {
        return sqlSession.selectList("EmailTemplateMapper.getUnbindTemplate",businessId);
    }
}
