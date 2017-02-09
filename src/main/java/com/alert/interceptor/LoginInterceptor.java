package com.alert.interceptor;

import com.alert.exception.UnAuthorizedException;
import com.alert.utils.AjaxUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Project: AlertSystem
 * Module Desc:com.juntu.alert.interceptor
 * User: zjprevenge
 * Date: 2016/12/6
 * Time: 15:26
 */
public class LoginInterceptor implements HandlerInterceptor {

    private static final Logger log = LoggerFactory.getLogger(LoginInterceptor.class);

    //不进行拦截的URL
    private String[] excludeUrls = {"css", "js", "img", "fonts",
            "scripts", "login", "loginPage", "register", "/error",
            "registerPage", "/info", "/health",
            "/metrics", "/trace", "/mappings",
            "/env", "/beans", "/dump", "/autoconfig",
            "/configprops"};

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object o) throws Exception {
        String requestURI = request.getRequestURI();
        for (String excludeUrl : excludeUrls) {
            if (requestURI.contains(excludeUrl)) {
                return true;
            }
        }
        //对session进行验证，是否失效
        HttpSession session = request.getSession(true);
        String name = (String) session.getAttribute("name");
        if (name == null) {
            //如果是ajax请求
            if (AjaxUtils.isAjax(request)) {
                response.setHeader("sessionStatus", "timeout");
                return false;
            } else {
                throw new UnAuthorizedException("需要登录才能访问");
            }
        } else {
            return true;
        }
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }

    public String[] getExcludeUrls() {
        return excludeUrls;
    }

    public void setExcludeUrls(String[] excludeUrls) {
        this.excludeUrls = excludeUrls;
    }
}
