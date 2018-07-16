package com.asc.holiday.service;

import com.asc.holiday.config.EmailConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

/**
 * User:dijian
 * Date:2018/6/28
 */
@Service
public class EmailServiceImpl implements IEmailServer {

    @Autowired
    private EmailConfig emailConfig;
    @Qualifier("mailSender")
    @Autowired
    private JavaMailSender mailSender;


    /**
     * 发送简单邮件
     * @param title   邮件主题
     * @param content 邮件内容
     */
    @Override
    public void sendSimpleMail(String title, String content) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(emailConfig.getEmailFrom());
        message.setTo(emailConfig.getEmailTo());
        message.setSubject(title);
        message.setText(content);
        mailSender.send(message);
    }
}
