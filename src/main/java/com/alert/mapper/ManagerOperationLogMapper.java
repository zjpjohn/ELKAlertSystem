package com.alert.mapper;

import com.alert.domain.ManagerOperationLog;

import java.util.List;
import java.util.Map;

/**
 * Project: AlertSystem
 * Module Desc:com.juntu.alert.mapper
 * User: zjprevenge
 * Date: 2016/11/4
 * Time: 14:19
 */
public interface ManagerOperationLogMapper {

    int addManagerOpLog(ManagerOperationLog managerOperationLog);

    List<ManagerOperationLog> getOperationLogByName(String managerName);

    List<ManagerOperationLog> getOperationLogByType(String operationType);

    List<ManagerOperationLog> getOperationLogByTime(Map<String, Object> params);

    List<ManagerOperationLog> getOpLogByNameAndTime(Map<String, Object> params);


}
