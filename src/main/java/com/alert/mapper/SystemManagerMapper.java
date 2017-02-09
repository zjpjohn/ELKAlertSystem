package com.alert.mapper;

import com.alert.domain.SystemManager;

import java.util.List;

/**
 * Project: AlertSystem
 * Module Desc:com.juntu.alert.mapper
 * User: zjprevenge
 * Date: 2016/11/4
 * Time: 14:21
 */
public interface SystemManagerMapper {

    int addSystemManager(SystemManager systemManager);

    int updateSystemManager(SystemManager systemManager);

    int deleteSystemManager(Integer id);

    List<SystemManager> getSystemManagerAll();

    SystemManager getSystemManagerByName(String name);
}
