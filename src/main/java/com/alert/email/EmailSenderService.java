package com.alert.email;

import com.google.common.collect.Lists;
import com.alert.domain.EmailTemplate;
import com.alert.domain.EmailUser;
import com.alert.domain.SendRecord;
import com.alert.domain.SenderEmail;
import com.alert.rule.BusinessMessage;
import com.alert.rule.Message;
import com.alert.rule.NginxMessage;
import com.alert.rule.RuleType;
import com.alert.service.EmailTemplateService;
import com.alert.service.EmailUserService;
import com.alert.service.OperationLogService;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Project: AlertSystem
 * Module Desc:com.juntu.alert.email
 * User: zjprevenge
 * Date: 2016/11/9
 * Time: 16:26
 */
@Service
public class EmailSenderService {

    private static final Logger log = LoggerFactory.getLogger(EmailSenderService.class);

    //带负载均衡的邮件发送者
    @Resource
    private LoadBalanceEmailSender loadBalanceSender;
    //邮件模板
    @Resource
    private EmailTemplateService emailTemplateService;
    //邮件接收者
    @Resource
    private EmailUserService emailUserService;
    //记录邮件发送日志
    @Resource
    private OperationLogService operationLogService;

    public void sendMessage(Message message) {
        if (StringUtils.isNotBlank(message.getType()) &&
                StringUtils.isNoneBlank(message.getTag())) {
            Map<String, String> model;
            String subject;
            try {
                //获取邮件模板
                EmailTemplate template = emailTemplateService.getEmailTemplateByTag(message.getType());
                //邮件模板不为空，进行邮件发送
                if (template != null) {
                    if (message.getTag().equals(RuleType.NGINX.getName())) {
                        NginxMessage nginxMessage = (NginxMessage) message;
                        subject = nginxMessage.getRequest() + "超时报警";
                        model = BeanUtils.describe(nginxMessage);
                    } else {
                        BusinessMessage businessMessage = (BusinessMessage) message;
                        subject = businessMessage.getType() + "[" + businessMessage.getFile() + "]错误告警";
                        model = BeanUtils.describe(businessMessage);
                    }
                    //获取邮件接受者
                    List<EmailUser> emailUsers = emailUserService.getEmailUserByTag(message.getType());
                    //邮件内容，将内容填充到freemarker模板中
                    String emailMessage = EmailTemplateConvert.freemarkerConvert(template, model);
                    //负责均衡获取发送邮箱
                    Map<SenderEmail, Session> senderMap = loadBalanceSender.loadBalance();
                    //发送邮件
                    sendTemplateEmail(subject, emailMessage, emailUsers, senderMap);
                    //添加邮件发送记录
                    sendEmailLog(message, emailMessage, senderMap);
                }
            } catch (Exception e) {
                log.info("handle email error:{}", e);
            }
        }
    }

    /**
     * 发送模板邮件
     *
     * @param subject    邮件主题
     * @param message    邮件内容
     * @param emailUsers 邮件接收者
     */
    private void sendTemplateEmail(String subject, String message, List<EmailUser> emailUsers, Map<SenderEmail, Session> emailSession) throws Exception {
        if (emailUsers != null) {
            for (EmailUser emailUser : emailUsers) {
                //获取发送者
                for (Map.Entry<SenderEmail, Session> senderEntry : emailSession.entrySet()) {
                    try {
                        SenderEmail email = senderEntry.getKey();
                        Session session = senderEntry.getValue();
                        //发送邮件信息
                        MimeMessage mime = new MimeMessage(session);
                        mime.setRecipient(javax.mail.Message.RecipientType.TO, new InternetAddress(emailUser.getEmail()));
                        mime.setSubject(subject);
                        mime.setFrom(new InternetAddress(email.getEmail()));
                        mime.setSentDate(new Date());
                        //发送带HTML邮件
                        Multipart multipart = new MimeMultipart();
                        MimeBodyPart bodyPart = new MimeBodyPart();
                        bodyPart.setContent(message, "text/html; charset=utf-8");
                        multipart.addBodyPart(bodyPart);
                        mime.setContent(multipart);
                        mime.saveChanges();
                        //发送邮件
                        Transport.send(mime, email.getEmail(), email.getPassword());
                    } catch (MessagingException e) {
                        log.error("send message error:{}", e);
                    }
                }
            }
        }
    }


    /**
     * 记录邮件发送记录
     *
     * @param message 消息内容
     */
    private void sendEmailLog(Message message, String content, Map<SenderEmail, Session> emailSession) {
        for (Map.Entry<SenderEmail, Session> sessionEntry : emailSession.entrySet()) {
            SenderEmail email = sessionEntry.getKey();
            SendRecord sendRecord = new SendRecord();
            sendRecord.setSendEmail(email.getEmail());
            sendRecord.setSendTime(new Date());
            sendRecord.setSendType(message.getType());
            if (content.length() > 500) {
                sendRecord.setSendContent(content.substring(0, 500));
            } else {
                sendRecord.setSendContent(content);
            }
            sendRecord.setSendUser(email.getEmail());
            operationLogService.addSendEmailRecord(Lists.newArrayList(sendRecord));
        }
    }
}
