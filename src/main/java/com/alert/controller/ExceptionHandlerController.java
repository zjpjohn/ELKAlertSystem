package com.alert.controller;

import com.alert.exception.UnAuthorizedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * Project: AlertSystem
 * Module Desc:com.juntu.alert.controller
 * User: zjprevenge
 * Date: 2016/12/7
 * Time: 17:27
 */
@ControllerAdvice
public class ExceptionHandlerController {

    /**
     * 出现未授权异常，直接跳转到登录页面
     *
     * @return
     */
    @ExceptionHandler(UnAuthorizedException.class)
    public String unAuthExceptionHandle() {
        return "redirect:/admin/loginPage";
    }
}
