package com.alert.mapper.impl;

import com.alert.domain.SenderEmail;
import com.alert.mapper.SenderEmailMapper;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

/**
 * Project: system
 * Module Desc:com.juntu.alert.mapper.impl
 * User: zjprevenge
 * Date: 2016/12/29
 * Time: 9:20
 */
@Repository
public class SenderEmailMapperImpl implements SenderEmailMapper {

    @Resource
    private SqlSession sqlSession;

    @Override
    public int addSenderEmail(SenderEmail senderEmail) {
        return sqlSession.insert("SenderEmailMapper.addSenderEmail", senderEmail);
    }

    @Override
    public int updateSenderEmail(SenderEmail senderEmail) {
        return sqlSession.update("SenderEmailMapper.updateSenderEmail", senderEmail);
    }

    @Override
    public int deleteSendEmail(Integer id) {
        return sqlSession.update("SenderEmailMapper.deleteSendEmail", id);
    }

    @Override
    public Integer activeSenderEmail(Integer id) {
        return sqlSession.update("SenderEmailMapper.activeSenderEmail", id);
    }

    @Override
    public List<SenderEmail> getSenderEmailAll() {
        return sqlSession.selectList("SenderEmailMapper.getSenderEmailAll");
    }

    @Override
    public SenderEmail getSenderEmailById(Integer id) {
        return sqlSession.selectOne("SenderEmailMapper.getSenderEmailById", id);
    }

    @Override
    public List<SenderEmail> getActiveSenderAll() {
        return sqlSession.selectList("SenderEmailMapper.getActiveSenderAll");
    }
}
