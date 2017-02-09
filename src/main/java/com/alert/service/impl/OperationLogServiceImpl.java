package com.alert.service.impl;

import com.alert.domain.ManagerOperationLog;
import com.alert.domain.SendRecord;
import com.alert.mapper.ManagerOperationLogMapper;
import com.alert.mapper.SendRecordMapper;
import com.alert.service.OperationLogService;
import com.google.common.base.Preconditions;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * Project: AlertSystem
 * Module Desc:com.juntu.alert.service.impl
 * User: zjprevenge
 * Date: 2016/12/9
 * Time: 14:56
 */
@Service
public class OperationLogServiceImpl implements OperationLogService {

    @Resource
    private ManagerOperationLogMapper managerOperationLogMapper;

    @Resource
    private SendRecordMapper sendRecordMapper;

    /**
     * 管理员操作日志
     *
     * @param operationLog 操作日志
     * @return
     */
    @Override
    @Transactional
    public int addManagerLog(ManagerOperationLog operationLog) {
        return managerOperationLogMapper.addManagerOpLog(operationLog);
    }

    /**
     * 查询操作类型相关的日志
     *
     * @param operationType
     * @return
     */
    @Override
    public List<ManagerOperationLog> operationLogByType(String operationType) {
        return managerOperationLogMapper.getOperationLogByType(operationType);
    }

    /**
     * 查询管理员的操作日志
     *
     * @param managerName
     * @return
     */
    @Override
    public List<ManagerOperationLog> operationLogByManagerName(String managerName) {
        return managerOperationLogMapper.getOperationLogByName(managerName);
    }

    /**
     * 查询管理员指定时间段内的操作日志
     *
     * @param params
     * @return
     */
    @Override
    public List<ManagerOperationLog> operationLogByNameAndTime(Map<String, Object> params) {
        return managerOperationLogMapper.getOpLogByNameAndTime(params);
    }

    /**
     * 批量添加邮件发送记录
     *
     * @param sendRecords 发送邮件记录
     * @return
     */
    @Override
    public int addSendEmailRecord(List<SendRecord> sendRecords) {
        Preconditions.checkNotNull(sendRecords);
        return sendRecordMapper.addEmailSendRecords(sendRecords);
    }

    /**
     * 查询指定业务线的告警邮件发送记录
     *
     * @param businessName 业务线名称
     * @return
     */
    @Override
    public List<SendRecord> getSendRecordsByBusinessLine(String businessName) {
        return sendRecordMapper.getSendRecordByType(businessName);
    }

    /**
     * 查询业务线指定时间段内的告警邮件发送记录
     *
     * @param params
     * @return
     */
    @Override
    public List<SendRecord> getSendRecordByLineAndTime(Map<String, Object> params) {
        return sendRecordMapper.getSendRecordTypeAndTime(params);
    }
}
