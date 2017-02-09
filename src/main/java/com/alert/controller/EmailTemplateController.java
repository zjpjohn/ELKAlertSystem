package com.alert.controller;

import com.alert.domain.EmailTemplate;
import com.alert.vo.JsonResult;
import com.google.common.collect.Maps;
import com.alert.service.EmailTemplateService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * Project: AlertSystem
 * Module Desc:com.juntu.alert.controller
 * User: zjprevenge
 * Date: 2016/11/4
 * Time: 12:38
 */
@Controller
@RequestMapping("/template")
public class EmailTemplateController {

    private static final Logger log = LoggerFactory.getLogger(EmailTemplateController.class);

    @Resource
    private EmailTemplateService emailTemplateService;

    @RequestMapping("/email")
    public ModelAndView templatePage() {
        ModelAndView view = new ModelAndView("email_template");
        try {
            List<EmailTemplate> templates = emailTemplateService.getEmailTemplateAll();
            view.addObject("code", 200);
            view.addObject("templates", templates);
        } catch (Exception e) {
            log.error("获取邮件模板失败：{}", e);
            view.addObject("code", 500);
            view.addObject("result", "查询出错咯");
        }
        return view;
    }

    /**
     * 添加邮件模板
     *
     * @param emailTemplate
     * @return
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public JsonResult addTemplate(EmailTemplate emailTemplate) {
        if (emailTemplate == null
                || StringUtils.isBlank(emailTemplate.getTemplateName())
                || StringUtils.isBlank(emailTemplate.getTemplateValue())) {
            return JsonResult.failure("参数不允许为空");
        }
        try {
            emailTemplateService.addEmailTemplate(emailTemplate);
            return JsonResult.success("添加邮件模板成功");
        } catch (Exception e) {
            log.error("添加邮件模板失败：{}", e);
        }
        return JsonResult.failure("添加邮件模板失败");
    }

    /**
     * 更新邮件模板
     *
     * @param emailTemplate
     * @return
     */
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ResponseBody
    public JsonResult updateTemplate(EmailTemplate emailTemplate) {
        if (emailTemplate == null
                || StringUtils.isBlank(emailTemplate.getTemplateName())
                || StringUtils.isBlank(emailTemplate.getTemplateValue())) {
            return JsonResult.failure("参数不允许为空");
        }
        try {
            emailTemplateService.updateEmailTemplate(emailTemplate);
            return JsonResult.success("更新邮件模板成功");
        } catch (Exception e) {
            log.error("更新邮件模板失败：{}", e);
        }
        return JsonResult.failure("更新邮件模板失败");
    }

    /**
     * 删除邮件模板
     *
     * @param id
     * @return
     */
    @RequestMapping("/delete/{id}")
    @ResponseBody
    public JsonResult deleteTemplate(@PathVariable("id") Integer id) {
        try {
            emailTemplateService.deleteEmailTemplate(id);
            return JsonResult.success("删除邮件模板成功");
        } catch (Exception e) {
            log.error("删除邮件模板失败：{}", e);
        }
        return JsonResult.failure("删除邮件模板失败");
    }

    /**
     * 查询指定邮件模板
     *
     * @param id
     * @return
     */
    @RequestMapping("/query/{id}")
    @ResponseBody
    public JsonResult<EmailTemplate> queryTemplate(@PathVariable("id") Integer id) {
        JsonResult<EmailTemplate> result;
        try {
            EmailTemplate emailTemplate = emailTemplateService.getEmailTemplateById(id);
            result = JsonResult.success(null).setData(emailTemplate);
        } catch (Exception e) {
            log.error("查询邮件模板失败：{}", e);
            result = JsonResult.failure("查询出错");
        }
        return result;
    }

    /**
     * 查询业务线所属的邮件模板
     *
     * @param businessName
     * @return
     */
    @RequestMapping("/email-by-business/{business}")
    public ModelAndView emailTemplateWithBusiness(@PathVariable("business") String businessName) {
        ModelAndView view = new ModelAndView("template_business");
        try {
            List<EmailTemplate> templates = emailTemplateService.getTemplateByBusinessName(businessName);
            view.addObject("code", 200);
            view.addObject("templates", templates);
        } catch (Exception e) {
            log.error("查询业务线所属邮件模板失败：{}", e);
            view.addObject("code", 500);
            view.addObject("result", "查询出错咯");
        }
        return view;
    }

    /**
     * 绑定邮件模板与业务线
     *
     * @param status     状态是否直接启用 0-只绑定邮件模板，1-只启用邮件模板,2-绑定并启用邮件模板
     * @param businessId 业务线id
     * @param templateId 模板id
     * @return
     */
    @RequestMapping("/binding/{status}/{templateId}/{businessId}")
    @ResponseBody
    public JsonResult bindingTemplate(@PathVariable("status") Integer status,
                                      @PathVariable("businessId") Integer businessId,
                                      @PathVariable("templateId") Integer templateId) {
        if (status == null
                || businessId == null
                || templateId == null) {
            return JsonResult.failure("参数不允许为空");
        }
        try {
            emailTemplateService.addAndActiveEmailTemplate(status, businessId, templateId);
            return JsonResult.success("操作成功");
        } catch (Exception e) {
            log.error("操作失败：{}", e);
        }
        return JsonResult.failure("操作失败");
    }

    /**
     * 解绑邮件模板
     *
     * @param businessId 业务线id
     * @param templateId 邮件模板id
     * @return
     */
    @RequestMapping("/unbinding/{templateId}/{businessId}")
    @ResponseBody
    public JsonResult unbindingTemplate(@PathVariable("businessId") Integer businessId,
                                        @PathVariable("templateId") Integer templateId) {
        JsonResult result;
        if (businessId == null
                || templateId == null) {
            return JsonResult.failure("参数不允许为空");
        }
        try {
            Map<String, Object> params = Maps.newHashMap();
            params.put("businessId", businessId);
            params.put("templateId", templateId);
            //正在使用的邮件模板不能进行解绑
            EmailTemplate emailTemplate = emailTemplateService.getActiveTemplateByBusinessId(params);
            if (emailTemplate != null) {
                result = JsonResult.failure("邮件模板正在使用，不能解绑");
            } else {
                emailTemplateService.unbindingTemplate(params);
                result = JsonResult.success("解绑邮件模板成功");
            }
        } catch (Exception e) {
            log.error("解绑邮件模板失败:{}", e);
            result = JsonResult.failure("解绑邮件模板失败");
        }
        return result;
    }

    /**
     * @param status     0-查询绑定的邮件模板，1-未绑定的邮件模板
     * @param businessId
     * @return
     */
    @RequestMapping("/bindOrUnbindTemplate/{businessId}/{status}")
    @ResponseBody
    public JsonResult<List<EmailTemplate>> bindOrUnbindTemplate(@PathVariable("status") Integer status,
                                                                @PathVariable("businessId") Integer businessId) {
        try {
            List<EmailTemplate> emailTemplates = null;
            if (status == 0) {
                emailTemplates = emailTemplateService.getBindEmailTemplate(businessId);
            } else {
                emailTemplates = emailTemplateService.getUnbindEmailTemplate(businessId);
            }
            return JsonResult.success(null).setData(emailTemplates);
        } catch (Exception e) {
            log.error("查询数据出现错误:{}", e);
        }
        return JsonResult.failure("加载数据失败");
    }

    @RequestMapping("/active/{id}")
    @ResponseBody
    public JsonResult activeTemplate(@PathVariable Integer id) {
        try {
            emailTemplateService.activeTemplate(id);
            return JsonResult.success("激活邮件模板成功");
        } catch (Exception e) {
            log.error("激活邮件模板失败：{}", e);
        }
        return JsonResult.failure("激活邮件模板失败");
    }
}
