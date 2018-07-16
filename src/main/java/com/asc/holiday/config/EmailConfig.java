package com.asc.holiday.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * User:dijian
 * Date:2018/6/28
 */
@Component
public class EmailConfig {

    @Value("${spring.mail.username}")
    private String emailFrom;

    @Value("${mail.to}")
    private String emailTo;

    public String getEmailFrom() {
        return emailFrom;
    }

    public void setEmailFrom(String emailFrom) {
        this.emailFrom = emailFrom;
    }

    public String getEmailTo() {
        return emailTo;
    }

    public void setEmailTo(String emailTo) {
        this.emailTo = emailTo;
    }
}
