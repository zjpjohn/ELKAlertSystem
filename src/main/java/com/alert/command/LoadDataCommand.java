package com.alert.command;

import com.alert.email.LoadBalanceEmailSender;
import com.alert.service.AlertRuleService;
import org.springframework.beans.factory.InitializingBean;

import javax.annotation.Resource;

/**
 * Project: JuntuAlertSystem
 * Module Desc:com.juntu.alert.command
 * User: zjprevenge
 * Date: 2016/12/30
 * Time: 14:59
 * 专门负责初始化完成加载缓存数据
 */
//@Component
public class LoadDataCommand implements InitializingBean {

    @Resource
    private AlertRuleService alertRuleService;

    @Resource
    private LoadBalanceEmailSender loadBalanceEmailSender;

    @Override
    public void afterPropertiesSet() throws Exception {

        //加载邮箱数据至缓存
        loadBalanceEmailSender.loadEmails();
        //加载规则至缓存中
        alertRuleService.loadRule();
    }
}
