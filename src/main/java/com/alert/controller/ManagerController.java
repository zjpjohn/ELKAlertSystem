package com.alert.controller;

import com.alert.domain.SystemManager;
import com.alert.service.SystemManagerService;
import com.alert.utils.MD5Utils;
import com.alert.vo.JsonResult;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Project: AlertSystem
 * Module Desc:com.juntu.alert.controller
 * User: zjprevenge
 * Date: 2016/11/4
 * Time: 12:37
 */
@Controller
@RequestMapping("/admin")
public class ManagerController {

    private static final Logger log = LoggerFactory.getLogger(ManagerController.class);

    @Resource
    private SystemManagerService systemManagerService;

    /**
     * 注册用户
     *
     * @param name
     * @param password
     * @param confirm
     * @param request
     * @return
     */
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    @ResponseBody
    public JsonResult<String> register(String name,
                                       String password,
                                       String confirm,
                                       HttpServletRequest request) {
        if (StringUtils.isBlank(name)
                || StringUtils.isBlank(password)
                || StringUtils.isBlank(confirm)) {
            return JsonResult.failure("参数不允许为空");
        }
        if (!password.equals(confirm)) {
            return JsonResult.failure("两次输入密码不同");
        }
        password = MD5Utils.md5(password);
        SystemManager systemManager = new SystemManager();
        systemManager.setName(name);
        systemManager.setPassword(password);
        JsonResult<String> jsonResult = systemManagerService.register(systemManager, "/index");
        if (jsonResult.getCode() == 200) {
            request.getSession().setAttribute("name", name);
        }
        return jsonResult;
    }

    /**
     * 注册用户界面
     *
     * @return
     */
    @RequestMapping("/registerPage")
    public ModelAndView registerPage() {
        ModelAndView view = new ModelAndView("register");
        return view;
    }

    /**
     * 登录页面
     *
     * @return
     */
    @RequestMapping("/loginPage")
    public ModelAndView loginPage() {
        ModelAndView view = new ModelAndView("login");
        return view;
    }

    /**
     * 登录
     *
     * @param name
     * @param password
     * @param request
     * @return
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    public JsonResult login(String name,
                            String password,
                            HttpServletRequest request) {
        if (StringUtils.isBlank(name)
                || StringUtils.isBlank(password)) {
            return JsonResult.failure("用户名或密码不允许为空");
        }
        if (systemManagerService.login(name, password)) {
            //登陆成功设置session
            HttpSession session = request.getSession(true);
            session.setAttribute("name", name);
            return JsonResult.success("登录成功").setData("/index");
        }
        return JsonResult.failure("登录失败");
    }

    @RequestMapping("/logout")
    public String logout(HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.invalidate();
        return "redirect:/admin/loginPage";
    }
}
