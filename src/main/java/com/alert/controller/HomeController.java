package com.alert.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * Project: AlertSystem
 * Module Desc:com.juntu.alert.controller
 * User: zjprevenge
 * Date: 2016/12/7
 * Time: 16:06
 */
@Controller
public class HomeController {

    /**
     * 网站首页 /index or /
     *
     * @return
     */
    @RequestMapping(value = {"/index", "/"})
    public ModelAndView index() {
        ModelAndView view = new ModelAndView("index");
        return view;
    }

    @RequestMapping("/home")
    public ModelAndView home() {
        return new ModelAndView("home");
    }
}
