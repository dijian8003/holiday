package com.asc.holiday.service;

/**
 * User:dijian
 * Date:2018/6/28
 */
public interface IEmailServer {
    /**
     * 发送简单邮件
     * @param title   邮件主题
     * @param content 邮件内容
     */
    void sendSimpleMail(String title, String content);
}
