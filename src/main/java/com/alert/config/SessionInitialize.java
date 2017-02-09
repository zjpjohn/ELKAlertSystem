package com.alert.config;

import org.springframework.core.annotation.Order;
import org.springframework.session.web.context.AbstractHttpSessionApplicationInitializer;

/**
 * Project: JuntuAlertSystem
 * Module Desc:com.juntu.alert.config
 * User: zjprevenge
 * Date: 2016/12/30
 * Time: 14:44
 */
@Order(100)
public class SessionInitialize extends AbstractHttpSessionApplicationInitializer {

}
