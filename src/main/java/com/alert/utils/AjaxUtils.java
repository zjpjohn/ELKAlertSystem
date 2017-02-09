package com.alert.utils;


import javax.servlet.http.HttpServletRequest;

/**
 * Project: system
 * Module Desc:com.juntu.alert.utils
 * User: zjprevenge
 * Date: 2016/12/23
 * Time: 8:53
 */
public class AjaxUtils {

    /**
     * 判断请求是否是ajax请求
     *
     * @param request
     * @return
     */
    public static boolean isAjax(HttpServletRequest request) {
        return (request.getHeader("X-Requested-With") != null
                && request.getHeader("X-Requested-With").equalsIgnoreCase("XMLHttpRequest"));
    }
}
