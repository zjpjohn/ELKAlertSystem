package com.alert.email;

import com.alert.domain.SenderEmail;
import com.google.common.collect.Maps;
import com.alert.service.SenderEmailService;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.mail.Session;
import java.util.List;
import java.util.Map;
import java.util.Properties;

/**
 * Project: AlertSystem
 * Module Desc:com.juntu.alert.email
 * User: zjprevenge
 * Date: 2016/11/9
 * Time: 14:19
 */
@Component
@PropertySource({"classpath:email.properties"})
public class LoadBalanceEmailSender {

    @Resource
    private Environment env;

    @Resource
    private EmailSenderCache emailSenderCache;

    @Resource
    private SenderEmailService senderEmailService;


    /**
     * 加载全部有效发送邮箱
     */
    public void loadEmails() {
        List<SenderEmail> emails = senderEmailService.getActiveSenderAll();
        emailSenderCache.reset();
        if (emails != null && emails.size() > 0) {
            emailSenderCache.putAll(emails);
        }
    }

    /**
     * 根据邮箱进行负载均衡，创建邮件发送器
     *
     * @return
     */
    public Map<SenderEmail, Session> loadBalance() {
        Map<SenderEmail, Session> map = Maps.newHashMap();
        SenderEmail email = emailSenderCache.getEmail();
        Properties props = new Properties();
        props.put("mail.smtp.auth", env.getProperty("email.auth"));// 将这个参数设为true，让服务器进行认证,认证用户名和密码是否正确
        props.put("mail.smtp.host", env.getProperty("email.host"));
        props.put("mail.transport.protocol", env.getProperty("email.protocal"));
        Session session = Session.getInstance(props);
        map.put(email, session);
        return map;
    }

}
