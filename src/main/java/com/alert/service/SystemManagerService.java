package com.alert.service;

import com.alert.domain.SystemManager;
import com.alert.vo.JsonResult;

/**
 * Project: AlertSystem
 * Module Desc:com.juntu.alert.service
 * User: zjprevenge
 * Date: 2016/12/6
 * Time: 13:38
 */
public interface SystemManagerService {

    boolean login(String name, String password);

    JsonResult<String> register(SystemManager systemManager, String redirect);

    boolean updateUser(SystemManager systemManager);

}
