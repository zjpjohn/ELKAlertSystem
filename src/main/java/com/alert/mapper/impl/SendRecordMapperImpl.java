package com.alert.mapper.impl;

import com.alert.mapper.SendRecordMapper;
import com.alert.domain.SendRecord;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * Project: system
 * Module Desc:com.juntu.alert.mapper.impl
 * User: zjprevenge
 * Date: 2016/12/29
 * Time: 9:21
 */
@Repository
public class SendRecordMapperImpl implements SendRecordMapper {

    @Resource
    private SqlSession sqlSession;

    @Override
    public Integer addEmailSendRecord(SendRecord sendRecord) {
        return sqlSession.insert("SendRecordMapper.addEmailSendRecord", sendRecord);
    }

    /**
     * 批量插入邮件发送记录
     *
     * @param sendRecords
     * @return
     */
    @Override
    public int addEmailSendRecords(List<SendRecord> sendRecords) {
        return sqlSession.insert("SendRecordMapper.addEmailSendRecords", sendRecords);
    }

    @Override
    public List<SendRecord> getSendRecordByType(String sendType) {
        return sqlSession.selectList("SendRecordMapper.getSendRecordByType", sendType);
    }

    @Override
    public List<SendRecord> getSendRecordTypeAndTime(Map<String, Object> params) {
        return sqlSession.selectList("SendRecordMapper.getSendRecordTypeAndTime", params);
    }
}
