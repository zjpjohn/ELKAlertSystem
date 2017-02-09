package com.alert.mapper;

import com.alert.domain.EmailUser;

import java.util.List;
import java.util.Map;

/**
 * Project: AlertSystem
 * Module Desc:com.juntu.alert.mapper
 * User: zjprevenge
 * Date: 2016/11/4
 * Time: 14:19
 */
public interface EmailUserMapper {

    int addEmailUser(EmailUser emailUser);

    int updateEmailUser(EmailUser emailUser);

    void deleteEmailUser(Integer id);

    //激活删除的邮件接收者
    Integer activeEmailUser(Integer id);

    EmailUser getEmailUserById(Integer id);

    EmailUser getEmailUserByName(String userName);

    EmailUser getEmailUserByEmail(String email);

    List<EmailUser> getEmailUserAll();

    List<EmailUser> getEmailUserByTypeId(Integer businessId);

    List<EmailUser> getUnbindEmailUser(Integer businessId);

    List<EmailUser> getEmailUserByType(String businessName);

    int correlateUserType(Map<String, Integer> params);

    int unbindingEmailWithBusiness(Map<String, Integer> params);
}
