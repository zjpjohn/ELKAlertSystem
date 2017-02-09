package com.alert.mapper;

import com.alert.domain.EmailTemplate;

import java.util.List;
import java.util.Map;

/**
 * Project: AlertSystem
 * Module Desc:com.juntu.alert.mapper
 * User: zjprevenge
 * Date: 2016/11/4
 * Time: 14:18
 */
public interface EmailTemplateMapper {

    Integer addEmailTemplate(EmailTemplate emailTemplate);

    int updateEmailTemplate(EmailTemplate emailTemplate);

    //激活已经删除的邮件模板
    int activeTemplate(Integer id);

    void deleteEmailTemplate(Integer templateId);

    void activeEmailTemplate(Map<String, Integer> params);

    void addAndActiveEmailTemplate(Map<String, Integer> params);

    int addMessageEmailTemplate(Map<String, Integer> params);

    EmailTemplate getEmailTemplateById(Integer id);

    EmailTemplate getEmailTemplateByName(String templateName);

    EmailTemplate getActiveTemplateByTag(String businessName);

    List<EmailTemplate> getTemplateByBusinessName(String businessName);

    List<EmailTemplate> getEmailTemplateAll();

    int unbindingTemplate(Map<String, Object> params);

    EmailTemplate getActiveTemplateByBusinessId(Map<String, Object> params);

    List<EmailTemplate> getBindTemplate(Integer businessId);

    List<EmailTemplate> getUnbindTemplate(Integer businessId);
}
