package com.alert.service.impl;

import com.alert.domain.EmailUser;
import com.alert.mapper.EmailUserMapper;
import com.alert.service.EmailUserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * Project: AlertSystem
 * Module Desc:com.juntu.alert.service.impl
 * User: zjprevenge
 * Date: 2016/11/9
 * Time: 17:03
 */
@Service
public class EmailUserServiceImpl implements EmailUserService {

    @Resource
    private EmailUserMapper emailUserMapper;

    /**
     * 查询消息类型对应的邮件发送者
     *
     * @param tag 消息类型标签
     * @return
     */
    @Override
    public List<EmailUser> getEmailUserByTag(String tag) {
        return emailUserMapper.getEmailUserByType(tag);
    }

    /**
     * 添加用户
     *
     * @return
     */
    @Override
    public EmailUser addEmailUser(EmailUser emailUser) {
        emailUserMapper.addEmailUser(emailUser);
        return emailUser;
    }

    /**
     * 更新用户
     *
     * @param emailUser
     * @return
     */
    @Override
    public Integer updateEmailUser(EmailUser emailUser) {
        return emailUserMapper.updateEmailUser(emailUser);
    }

    /**
     * 删除用户
     *
     * @param id
     * @return
     */
    @Override
    public void deleteEmailUser(Integer id) {
        emailUserMapper.deleteEmailUser(id);
    }

    @Override
    public EmailUser getEmailUserById(Integer id) {
        return emailUserMapper.getEmailUserById(id);
    }

    @Override
    public EmailUser getEmailUserByName(String name) {
        return emailUserMapper.getEmailUserByName(name);
    }

    @Override
    public EmailUser getEmailUserByEmail(String email) {
        return emailUserMapper.getEmailUserByEmail(email);
    }

    /**
     * 根据业务线id获取邮件用户
     *
     * @param businessId
     * @return
     */
    @Override
    public List<EmailUser> getEmailUsersByBusinessId(Integer businessId) {
        return emailUserMapper.getEmailUserByTypeId(businessId);
    }

    /**
     * 根据业务线名称获取邮件用户
     *
     * @param businessName
     * @return
     */
    @Override
    public List<EmailUser> getEmailUsersByBusinessName(String businessName) {
        return emailUserMapper.getEmailUserByType(businessName);
    }

    /**
     * 绑定用户与业务线
     *
     * @param params
     */
    @Override
    public void bindingEmailUserWithBusiness(Map<String, Integer> params) {
        emailUserMapper.correlateUserType(params);
    }

    @Override
    public List<EmailUser> getEmailUserAll() {
        return emailUserMapper.getEmailUserAll();
    }

    /**
     * 接触告警邮箱与业务线的绑定
     *
     * @param params
     * @return
     */
    @Override
    public int unbindingEmailWithBusiness(Map<String, Integer> params) {
        return emailUserMapper.unbindingEmailWithBusiness(params);
    }

    /**
     * 获取当前业务线未绑定的告警用户
     *
     * @param businessId 当前业务线的id
     * @return
     */
    @Override
    public List<EmailUser> getUnbindEmailUsers(Integer businessId) {
        return emailUserMapper.getUnbindEmailUser(businessId);
    }

    @Override
    public void activeEmailUser(Integer id) {
        emailUserMapper.activeEmailUser(id);
    }
}
