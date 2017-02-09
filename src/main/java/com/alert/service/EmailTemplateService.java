package com.alert.service;

import com.alert.domain.EmailTemplate;

import java.util.List;
import java.util.Map;

/**
 * Project: AlertSystem
 * Module Desc:com.juntu.alert.service
 * User: zjprevenge
 * Date: 2016/11/9
 * Time: 17:10
 */
public interface EmailTemplateService {

    /**
     * 获取消息类型正在使用的邮件模板
     *
     * @param tag 消息类型标签
     * @return
     */
    EmailTemplate getEmailTemplateByTag(String tag);

    /**
     * 获取业务线相关的邮件模板
     *
     * @param businessName
     * @return
     */
    List<EmailTemplate> getTemplateByBusinessName(String businessName);

    /**
     * 添加邮件模板
     *
     * @param emailTemplate
     * @return
     */
    EmailTemplate addEmailTemplate(EmailTemplate emailTemplate);

    /**
     * 更新邮件模板
     *
     * @param emailTemplate
     * @return
     */
    Integer updateEmailTemplate(EmailTemplate emailTemplate);

    /**
     * 删除邮件模板
     *
     * @param id
     */
    void deleteEmailTemplate(Integer id);

    /**
     * 激活邮件模板
     *
     * @param params
     */
    void activeEmailTemplate(Map<String, Integer> params);

    /**
     * 绑定并启用邮件模板
     *
     * @param status
     * @param businessId
     * @param templateId
     */
    void addAndActiveEmailTemplate(Integer status, Integer businessId, Integer templateId);


    List<EmailTemplate> getEmailTemplateAll();

    EmailTemplate getEmailTemplateById(Integer id);

    void unbindingTemplate(Map<String, Object> params);

    EmailTemplate getActiveTemplateByBusinessId(Map<String, Object> params);

    /**
     * 获取已经绑定的邮件模板
     *
     * @param businessId 业务线id
     * @return
     */
    List<EmailTemplate> getBindEmailTemplate(Integer businessId);

    /**
     * 获取业务线未绑定的邮件模板
     *
     * @param businessId 业务线id
     * @return
     */
    List<EmailTemplate> getUnbindEmailTemplate(Integer businessId);

    void activeTemplate(Integer id);
}
