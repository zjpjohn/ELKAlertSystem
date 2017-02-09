package com.alert.controller;

import com.alert.domain.BusinessLine;
import com.google.common.collect.Maps;
import com.alert.domain.EmailUser;
import com.alert.service.BusinessLineService;
import com.alert.service.EmailUserService;
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
import java.util.Map;

/**
 * Project: AlertSystem
 * Module Desc:com.juntu.alert.controller
 * User: zjprevenge
 * Date: 2016/11/4
 * Time: 12:38
 */
@Controller
@RequestMapping("/email")
public class EmailController {

    private static final Logger log = LoggerFactory.getLogger(EmailController.class);

    public static final String BIND = "bind";

    public static final String UNBIND = "unbind";

    @Resource
    private EmailUserService emailUserService;

    @Resource
    private BusinessLineService businessLineService;

    /**
     * 负责人邮箱查询列表页面
     *
     * @return
     */
    @RequestMapping("/emailusers")
    public ModelAndView emailUserAll() {
        ModelAndView view = new ModelAndView("email_users");
        try {
            List<EmailUser> emailUsers = emailUserService.getEmailUserAll();
            view.addObject("code", 200);
            view.addObject("emailUsers", emailUsers);
        } catch (Exception e) {
            log.error("查询负责人邮箱出错：{}", e);
            view.addObject("code", 500);
            view.addObject("result", "查询出错咯");
        }
        return view;
    }

    /**
     * 添加负责人邮箱
     *
     * @param emailUser 邮箱内容
     * @return
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public JsonResult addEmail(EmailUser emailUser) {
        if (emailUser == null) {
            return JsonResult.failure("参数不允许为空");
        }
        try {
            EmailUser user = emailUserService.getEmailUserByName(emailUser.getUserName());
            if (user != null) {
                return JsonResult.failure("添加的用户已经存在");
            }
            emailUserService.addEmailUser(emailUser);
            return JsonResult.success("添加邮箱成功");
        } catch (Exception e) {
            log.error("添加邮箱出错：{}", e);

        }
        return JsonResult.failure("添加邮箱出错");
    }

    /**
     * 更新邮箱
     *
     * @param emailUser 待更新的邮箱
     * @return
     */
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ResponseBody
    public JsonResult updateEmail(EmailUser emailUser) {
        if (emailUser == null) {
            return JsonResult.failure("参数不允许为空");
        }
        try {
            emailUserService.updateEmailUser(emailUser);
            return JsonResult.success("更新邮箱成功");
        } catch (Exception e) {
            log.error("更新邮箱出错：{}", e);
        }
        return JsonResult.failure("更新邮箱出错");
    }

    @RequestMapping("/delete/{id}")
    @ResponseBody
    public JsonResult deleteEmail(@PathVariable("id") Integer id) {
        if (id == null) {
            return JsonResult.failure("参数不允许为空");
        }
        try {
            emailUserService.deleteEmailUser(id);
            return JsonResult.success("删除邮箱成功");
        } catch (Exception e) {
            log.error("删除邮箱失败：{}", e);
        }
        return JsonResult.failure("删除失败");
    }

    @RequestMapping("/query/{id}")
    @ResponseBody
    public JsonResult<EmailUser> queryEmailUser(@PathVariable("id") Integer id) {
        try {
            EmailUser emailUser = emailUserService.getEmailUserById(id);
            return JsonResult.success(null).setData(emailUser);
        } catch (Exception e) {
            log.error("查询邮箱地址出错：{}", e);
        }
        return JsonResult.failure("查询出错咯");
    }

    /**
     * 绑定告警邮箱到制定业务线
     *
     * @param userId     邮箱id
     * @param businessId 业务线id
     * @return
     */
    @RequestMapping("/binding/{userId}/{businessId}")
    @ResponseBody
    public JsonResult bindingEmailToBusiness(@PathVariable("userId") Integer userId,
                                             @PathVariable("businessId") Integer businessId) {
        if (userId == null
                || businessId == null) {
            return JsonResult.failure("参数不允许为空");
        }
        try {
            Map<String, Integer> params = Maps.newHashMap();
            params.put("businessId", businessId);
            params.put("userId", userId);
            emailUserService.bindingEmailUserWithBusiness(params);
            return JsonResult.success("绑定告警邮箱成功");
        } catch (Exception e) {
            log.error("绑定告警邮箱至业务线失败：{}", e);
        }
        return JsonResult.failure("绑定告警邮箱失败");
    }

    /**
     * 解除邮箱绑定
     *
     * @param userId     邮箱id
     * @param businessId 业务线id
     * @return
     */
    @RequestMapping("/unbinding/{userId}/{businessId}")
    @ResponseBody
    public JsonResult unbindingEmailWithBusiness(@PathVariable("userId") Integer userId,
                                                 @PathVariable("businessId") Integer businessId) {
        if (userId == null
                || businessId == null) {
            return JsonResult.failure("参数不允许为空");
        }
        try {
            Map<String, Integer> params = Maps.newHashMap();
            params.put("businessId", businessId);
            params.put("userId", userId);
            emailUserService.unbindingEmailWithBusiness(params);
            return JsonResult.success("解除邮箱绑定成功");
        } catch (Exception e) {
            log.error("解除邮箱绑定失败：{}", e);
        }
        return JsonResult.failure("解除邮箱绑定失败");
    }

    /**
     * 查询告警用户绑定或未绑定的业务线
     *
     * @param type
     * @param id
     * @return
     */
    @RequestMapping("/bind-or-unbind/{type}/{id}")
    @ResponseBody
    public JsonResult<List<BusinessLine>> bindOrUnbindBusinessLine(@PathVariable("type") String type,
                                                                   @PathVariable("id") Integer id) {
        if (StringUtils.isBlank(type)
                || id == null) {
            return JsonResult.failure("参数不允许为空");
        }
        try {
            List<BusinessLine> businessLines = null;
            if (BIND.equals(type)) {
                businessLines = businessLineService.userBindBusinessLine(id);
            } else {
                businessLines = businessLineService.userUnbindBusinessLine(id);
            }
            return JsonResult.success(null).setData(businessLines);
        } catch (Exception e) {
            log.error("加载业务线数据出错：{}", e);
        }
        return JsonResult.failure("加载业务线数据出错");
    }

    /**
     * 查询业务线绑定的用户
     *
     * @param status     0-已绑定的用户，1-未绑定的用户
     * @param businessId
     * @return
     */
    @RequestMapping("/bindOr/{businessId}/{status}")
    @ResponseBody
    public JsonResult<List<EmailUser>> bindOrUnbindUserInfos(@PathVariable Integer status,
                                                             @PathVariable Integer businessId) {
        try {
            List<EmailUser> emailUserList = null;
            if (status == 0) {
                emailUserList = emailUserService.getEmailUsersByBusinessId(businessId);
            } else {
                emailUserList = emailUserService.getUnbindEmailUsers(businessId);
            }
            return JsonResult.success(null).setData(emailUserList);
        } catch (Exception e) {
            log.error("查询数据出错：{}", e);
        }
        return JsonResult.failure("加载数据出现错误");
    }

    @RequestMapping("/active/{id}")
    @ResponseBody
    public JsonResult activeEmailUser(@PathVariable Integer id) {
        try {
            emailUserService.activeEmailUser(id);
            return JsonResult.success("激活接收者成功");
        } catch (Exception e) {
            log.error("激活邮件接受者失败：{}", e);
        }
        return JsonResult.failure("激活接受者失败");
    }
}
