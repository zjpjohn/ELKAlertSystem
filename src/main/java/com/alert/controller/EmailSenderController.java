package com.alert.controller;

import com.alert.domain.SenderEmail;
import com.alert.service.SenderEmailService;
import com.alert.vo.JsonResult;
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

/**
 * Project: AlertSystem
 * Module Desc:com.juntu.alert.controller
 * User: zjprevenge
 * Date: 2016/12/12
 * Time: 10:56
 */
@Controller
@RequestMapping("/sender")
public class EmailSenderController {

    private static final Logger log = LoggerFactory.getLogger(EmailSenderController.class);

    @Resource
    private SenderEmailService senderEmailService;

    @RequestMapping("/query")
    public ModelAndView sendersAll() {
        ModelAndView view = new ModelAndView("email_senders");
        try {
            List<SenderEmail> senderEmails = senderEmailService.getSenderAll();
            view.addObject("code", 200);
            view.addObject("senderEmails", senderEmails);
        } catch (Exception e) {
            log.error("加载邮箱发送列表出错：{}", e);
            view.addObject("code", 500);
            view.addObject("result", "加载邮箱列表出错咯！");
        }
        return view;
    }

    @RequestMapping("/add")
    @ResponseBody
    public JsonResult addSender(String email, String password) {
        if (StringUtils.isBlank(email)
                || StringUtils.isBlank(password)) {
            return JsonResult.failure("参数不允许为空");
        }
        try {
            senderEmailService.addSender(email, password);
            return JsonResult.success("添加发送邮箱成功");
        } catch (Exception e) {
            log.error("添加邮件发送者失败：{}", e);
        }
        return JsonResult.failure("添加发送邮箱失败");
    }

    /**
     * 加载要更新的数据
     *
     * @param id
     * @return
     */
    @RequestMapping("/updateInfo/{id}")
    @ResponseBody
    public JsonResult updateEmailInfo(@PathVariable("id") Integer id) {
        try {
            SenderEmail senderEmail = senderEmailService.getSenderById(id);
            return JsonResult.success(null).setData(senderEmail);
        } catch (Exception e) {
            log.error("加载邮箱信息失败：{}", e);
        }
        return JsonResult.failure("加载数据出错咯");
    }

    /**
     * 更新邮箱信息
     *
     * @param senderEmail
     * @return
     */
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ResponseBody
    public JsonResult updateSender(SenderEmail senderEmail) {
        if (senderEmail == null) {
            return JsonResult.failure("参数不允许为空");
        }
        try {
            senderEmailService.updateSender(senderEmail);
            return JsonResult.success("更新发送邮箱成功");
        } catch (Exception e) {
            log.error("更新发送邮箱失败：{}", e);
        }
        return JsonResult.failure("更新发送邮箱失败");
    }

    @RequestMapping("/delete/{id}")
    @ResponseBody
    public JsonResult deleteSender(@PathVariable("id") Integer id) {
        try {
            senderEmailService.deleteSender(id);
            return JsonResult.success("删除发送邮箱发送者");
        } catch (Exception e) {
            log.error("删除发送邮箱失败：{}", e);
        }
        return JsonResult.failure("删除发送邮箱失败");
    }

    /**
     * 激活发送邮箱
     *
     * @param id
     * @return
     */
    @RequestMapping("/active/{id}")
    @ResponseBody
    public JsonResult activeSenderEmail(@PathVariable Integer id) {
        try {
            senderEmailService.activeSenderEmail(id);
            return JsonResult.success("激活发送邮箱成功");
        } catch (Exception e) {
            log.error("激活发送邮箱失败：{}", e);
        }
        return JsonResult.failure("激活发送邮箱失败");
    }
}
