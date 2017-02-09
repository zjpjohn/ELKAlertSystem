package com.alert.mapper.impl;

import com.alert.domain.BusinessLine;
import com.alert.mapper.BusinessLineMapper;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

/**
 * Project: system
 * Module Desc:com.juntu.alert.mapper.impl
 * User: zjprevenge
 * Date: 2016/12/29
 * Time: 9:17
 */
@Repository
public class BusinessLineMapperImpl implements BusinessLineMapper {

    @Resource
    private SqlSession sqlSession;

    @Override
    public BusinessLine getBusinessLineById(Integer id) {
        return sqlSession.selectOne("BusinessLineMapper.getBusinessLineById", id);
    }

    @Override
    public BusinessLine getBusinessLineByName(String name) {
        return sqlSession.selectOne("BusinessLineMapper.getBusinessLineByName", name);
    }

    @Override
    public List<BusinessLine> getBusinessLineAll() {
        return sqlSession.selectList("BusinessLineMapper.getBusinessLineAll");
    }

    @Override
    public Integer addBusinessLine(BusinessLine businessLine) {
        return sqlSession.insert("BusinessLineMapper.addBusinessLine", businessLine);
    }

    @Override
    public Integer updateBusinessLine(BusinessLine businessLine) {
        return sqlSession.update("BusinessLineMapper.updateBusinessLine", businessLine);
    }

    @Override
    public Integer deleteBusinessLine(Integer id) {
        return sqlSession.update("BusinessLineMapper.deleteBusinessLine", id);
    }

    /**
     * 激活已经删除的业务线
     *
     * @param id
     * @return
     */
    @Override
    public Integer activeBusinessLine(Integer id) {
        return sqlSession.update("BusinessLineMapper.activeBusinessLine",id);
    }

    /**
     * 获取用户已经绑定的业务线
     *
     * @param userId 用户id
     * @return
     */
    @Override
    public List<BusinessLine> getBindBusinessLines(Integer userId) {
        return sqlSession.selectList("BusinessLineMapper.getBindBusinessLines",userId);
    }

    /**
     * 获取用户未绑定的业务线
     *
     * @param userId 用户id
     * @return
     */
    @Override
    public List<BusinessLine> getUnbindBusinessLines(Integer userId) {
        return sqlSession.selectList("BusinessLineMapper.getUnbindBusinessLines",userId);
    }
}
