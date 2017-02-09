package com.alert.service;

import com.alert.domain.EmailUser;

import java.util.List;
import java.util.Map;

/**
 * Project: AlertSystem
 * Module Desc:com.juntu.alert.service
 * User: zjprevenge
 * Date: 2016/11/9
 * Time: 17:01
 */
public interface EmailUserService {

    /**
     * 查询消息类型对应的邮件发送者
     *
     * @param tag 消息类型标签
     * @return
     */
    List<EmailUser> getEmailUserByTag(String tag);

    /**
     * 添加用户
     *
     * @return
     */
    EmailUser addEmailUser(EmailUser emailUser);

    /**
     * 更新用户
     *
     * @param emailUser
     * @return
     */
    Integer updateEmailUser(EmailUser emailUser);

    /**
     * 删除用户
     *
     * @param id
     * @return
     */
    void deleteEmailUser(Integer id);

    EmailUser getEmailUserById(Integer id);

    EmailUser getEmailUserByName(String name);

    EmailUser getEmailUserByEmail(String email);

    /**
     * 根据业务线id获取邮件用户
     *
     * @param businessId
     * @return
     */
    List<EmailUser> getEmailUsersByBusinessId(Integer businessId);

    /**
     * 根据业务线名称获取邮件用户
     *
     * @param businessName
     * @return
     */
    List<EmailUser> getEmailUsersByBusinessName(String businessName);

    /**
     * 绑定用户与业务线
     *
     * @param params
     */
    void bindingEmailUserWithBusiness(Map<String, Integer> params);


    List<EmailUser> getEmailUserAll();

    /**
     * 接触告警邮箱与业务线的绑定
     *
     * @param params
     * @return
     */
    int unbindingEmailWithBusiness(Map<String, Integer> params);

    /**
     * 获取当前业务线未绑定的告警用户
     *
     * @param businessId 当前业务线的id
     * @return
     */
    List<EmailUser> getUnbindEmailUsers(Integer businessId);

    void activeEmailUser(Integer id);
}
