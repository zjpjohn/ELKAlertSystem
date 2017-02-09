package com.alert.mapper;

import com.alert.domain.SendRecord;

import java.util.List;
import java.util.Map;

/**
 * Project: AlertSystem
 * Module Desc:com.juntu.alert.mapper
 * User: zjprevenge
 * Date: 2016/11/4
 * Time: 14:20
 */
public interface SendRecordMapper {

    Integer addEmailSendRecord(SendRecord sendRecord);

    /**
     * 批量插入邮件发送记录
     *
     * @param SendRecords
     * @return
     */
    int addEmailSendRecords(List<SendRecord> SendRecords);

    List<SendRecord> getSendRecordByType(String sendType);

    List<SendRecord> getSendRecordTypeAndTime(Map<String, Object> params);
}
