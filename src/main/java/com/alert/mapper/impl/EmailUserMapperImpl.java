package com.alert.mapper.impl;

import com.alert.domain.EmailUser;
import com.alert.mapper.EmailUserMapper;
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
 * Time: 9:19
 */
@Repository
public class EmailUserMapperImpl implements EmailUserMapper {

    @Resource
    private SqlSession sqlSession;

    @Override
    public int addEmailUser(EmailUser emailUser) {
        return sqlSession.insert("EmailUserMapper.addEmailUser", emailUser);
    }

    @Override
    public int updateEmailUser(EmailUser emailUser) {
        return sqlSession.update("EmailUserMapper.updateEmailUser", emailUser);
    }

    @Override
    public void deleteEmailUser(Integer id) {
        sqlSession.update("EmailUserMapper.deleteEmailUser", id);
    }

    @Override
    public Integer activeEmailUser(Integer id) {
        return sqlSession.update("EmailUserMapper.activeEmailUser", id);
    }

    @Override
    public EmailUser getEmailUserById(Integer id) {
        return sqlSession.selectOne("EmailUserMapper.getEmailUserById", id);
    }

    @Override
    public EmailUser getEmailUserByName(String userName) {
        return sqlSession.selectOne("EmailUserMapper.getEmailUserByName", userName);
    }

    @Override
    public EmailUser getEmailUserByEmail(String email) {
        return sqlSession.selectOne("EmailUserMapper.getEmailUserByEmail", email);
    }

    @Override
    public List<EmailUser> getEmailUserAll() {
        return sqlSession.selectList("EmailUserMapper.getEmailUserAll");
    }

    @Override
    public List<EmailUser> getEmailUserByTypeId(Integer businessId) {
        return sqlSession.selectList("EmailUserMapper.getEmailUserByTypeId", businessId);
    }

    @Override
    public List<EmailUser> getUnbindEmailUser(Integer businessId) {
        return sqlSession.selectList("EmailUserMapper.getUnbindEmailUser", businessId);
    }

    @Override
    public List<EmailUser> getEmailUserByType(String businessName) {
        return sqlSession.selectList("EmailUserMapper.getEmailUserByType", businessName);
    }

    @Override
    public int correlateUserType(Map<String, Integer> params) {
        return sqlSession.insert("EmailUserMapper.correlateUserType", params);
    }

    @Override
    public int unbindingEmailWithBusiness(Map<String, Integer> params) {
        return sqlSession.update("EmailUserMapper.unbindingEmailWithBusiness", params);
    }
}
