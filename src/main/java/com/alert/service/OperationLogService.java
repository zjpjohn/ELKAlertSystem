package com.alert.service;

import com.alert.domain.ManagerOperationLog;
import com.alert.domain.SendRecord;

import java.util.List;
import java.util.Map;

/**
 * Project: AlertSystem
 * Module Desc:com.juntu.alert.service
 * User: zjprevenge
 * Date: 2016/12/6
 * Time: 13:39
 */
public interface OperationLogService {

    /**
     * 管理员操作日志
     *
     * @param operationLog 操作日志
     * @return
     */
    int addManagerLog(ManagerOperationLog operationLog);

    /**
     * 查询操作类型相关的日志
     *
     * @param operationType
     * @return
     */
    List<ManagerOperationLog> operationLogByType(String operationType);

    /**
     * 查询管理员的操作日志
     *
     * @param managerName
     * @return
     */
    List<ManagerOperationLog> operationLogByManagerName(String managerName);

    /**
     * 查询管理员指定时间段内的操作日志
     *
     * @param params
     * @return
     */
    List<ManagerOperationLog> operationLogByNameAndTime(Map<String, Object> params);

    /**
     * 批量添加邮件发送记录
     *
     * @param sendRecords 发送邮件记录
     * @return
     */
    int addSendEmailRecord(List<SendRecord> sendRecords);


    /**
     * 查询指定业务线的告警邮件发送记录
     *
     * @param businessName 业务线名称
     * @return
     */
    List<SendRecord> getSendRecordsByBusinessLine(String businessName);

    /**
     * 查询业务线指定时间段内的告警邮件发送记录
     *
     * @param params
     * @return
     */
    List<SendRecord> getSendRecordByLineAndTime(Map<String, Object> params);
}
