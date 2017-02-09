package com.alert.service.impl;

import com.alert.domain.EmailTemplate;
import com.google.common.collect.Maps;
import com.alert.mapper.EmailTemplateMapper;
import com.alert.service.EmailTemplateService;
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
 * Time: 17:11
 */
@Service
public class EmailTemplateServiceImpl implements EmailTemplateService {

    @Resource
    private EmailTemplateMapper emailTemplateMapper;

    /**
     * 获取消息类型正在使用的邮件模板
     *
     * @param tag 消息类型标签
     * @return
     */
    @Override
    public EmailTemplate getEmailTemplateByTag(String tag) {
        return emailTemplateMapper.getActiveTemplateByTag(tag);
    }

    /**
     * 获取业务线相关的邮件模板
     *
     * @param businessName
     * @return
     */
    @Override
    public List<EmailTemplate> getTemplateByBusinessName(String businessName) {
        return emailTemplateMapper.getTemplateByBusinessName(businessName);
    }

    /**
     * 添加邮件模板
     *
     * @param emailTemplate
     * @return
     */
    @Override
    public EmailTemplate addEmailTemplate(EmailTemplate emailTemplate) {
        emailTemplateMapper.addEmailTemplate(emailTemplate);
        return emailTemplate;
    }

    /**
     * 更新邮件模板
     *
     * @param emailTemplate
     * @return
     */
    @Override
    public Integer updateEmailTemplate(EmailTemplate emailTemplate) {
        return emailTemplateMapper.updateEmailTemplate(emailTemplate);
    }

    /**
     * 删除邮件模板
     *
     * @param id
     */
    @Override
    public void deleteEmailTemplate(Integer id) {
        emailTemplateMapper.deleteEmailTemplate(id);
    }

    /**
     * 激活邮件模板
     *
     * @param params
     */
    @Override
    @Transactional
    public void activeEmailTemplate(Map<String, Integer> params) {
        emailTemplateMapper.activeEmailTemplate(params);
    }

    /**
     * 绑定并启用邮件模板
     *
     * @param status
     * @param businessId
     * @param templateId
     */
    @Override
    @Transactional
    public void addAndActiveEmailTemplate(Integer status,
                                          Integer businessId,
                                          Integer templateId) {
        Map<String, Integer> params = Maps.newHashMap();
        params.put("status", status);
        params.put("businessId", businessId);
        params.put("templateId", templateId);
        emailTemplateMapper.addAndActiveEmailTemplate(params);
    }

    @Override
    public List<EmailTemplate> getEmailTemplateAll() {
        return emailTemplateMapper.getEmailTemplateAll();
    }

    @Override
    public EmailTemplate getEmailTemplateById(Integer id) {
        return emailTemplateMapper.getEmailTemplateById(id);
    }

    @Override
    public void unbindingTemplate(Map<String, Object> params) {
        emailTemplateMapper.unbindingTemplate(params);
    }

    @Override
    public EmailTemplate getActiveTemplateByBusinessId(Map<String, Object> params) {
        return emailTemplateMapper.getActiveTemplateByBusinessId(params);
    }

    /**
     * 获取已经绑定的邮件模板
     *
     * @param businessId 业务线id
     * @return
     */
    @Override
    public List<EmailTemplate> getBindEmailTemplate(Integer businessId) {
        return emailTemplateMapper.getBindTemplate(businessId);
    }

    /**
     * 获取业务线未绑定的邮件模板
     *
     * @param businessId 业务线id
     * @return
     */
    @Override
    public List<EmailTemplate> getUnbindEmailTemplate(Integer businessId) {
        return emailTemplateMapper.getUnbindTemplate(businessId);
    }

    @Override
    public void activeTemplate(Integer id) {
        emailTemplateMapper.activeTemplate(id);
    }
}
