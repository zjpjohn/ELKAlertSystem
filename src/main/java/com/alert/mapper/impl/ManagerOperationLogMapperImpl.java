package com.alert.mapper.impl;

import com.alert.domain.ManagerOperationLog;
import com.alert.mapper.ManagerOperationLogMapper;
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
 * Time: 9:20
 */
@Repository
public class ManagerOperationLogMapperImpl implements ManagerOperationLogMapper {

    @Resource
    private SqlSession sqlSession;

    @Override
    public int addManagerOpLog(ManagerOperationLog managerOperationLog) {
        return sqlSession.insert("ManagerOperationLogMapper.addManagerOpLog", managerOperationLog);
    }

    @Override
    public List<ManagerOperationLog> getOperationLogByName(String managerName) {
        return sqlSession.selectList("ManagerOperationLogMapper.getOperationLogByName", managerName);
    }

    @Override
    public List<ManagerOperationLog> getOperationLogByType(String operationType) {
        return sqlSession.selectList("ManagerOperationLogMapper.getOperationLogByType", operationType);
    }

    @Override
    public List<ManagerOperationLog> getOperationLogByTime(Map<String, Object> params) {
        return sqlSession.selectList("ManagerOperationLogMapper.getOperationLogByTime", params);
    }

    @Override
    public List<ManagerOperationLog> getOpLogByNameAndTime(Map<String, Object> params) {
        return sqlSession.selectList("ManagerOperationLogMapper.getOpLogByNameAndTime", params);
    }
}
