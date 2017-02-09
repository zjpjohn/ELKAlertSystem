package com.alert.mapper.impl;

import com.alert.mapper.SystemManagerMapper;
import com.alert.domain.SystemManager;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

/**
 * Project: system
 * Module Desc:com.juntu.alert.mapper.impl
 * User: zjprevenge
 * Date: 2016/12/29
 * Time: 9:22
 */
@Repository
public class SystemManagerMapperImpl implements SystemManagerMapper {

    @Resource
    private SqlSession sqlSession;

    @Override
    public int addSystemManager(SystemManager systemManager) {
        return sqlSession.insert("SystemManagerMapper.addSystemManager",systemManager);
    }

    @Override
    public int updateSystemManager(SystemManager systemManager) {
        return sqlSession.update("SystemManagerMapper.updateSystemManager",systemManager);
    }

    @Override
    public int deleteSystemManager(Integer id) {
        return sqlSession.update("SystemManagerMapper.deleteSystemManager", id);
    }

    @Override
    public List<SystemManager> getSystemManagerAll() {
        return sqlSession.selectList("SystemManagerMapper.getSystemManagerAll");
    }

    @Override
    public SystemManager getSystemManagerByName(String name) {
        return sqlSession.selectOne("SystemManagerMapper.getSystemManagerByName",name);
    }
}
