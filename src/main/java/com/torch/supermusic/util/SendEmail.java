package com.torch.supermusic.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;


@Service
public class SendEmail {
    @Autowired
    private JavaMailSenderImpl mailSender;
    @Value("${spring.mail.username}")
    private String from;

    /**
     * 发送纯文本邮件
     * @param to       收件人地址
     * @param subject  邮件标题
     * @param text     邮件内容
     */
    public void sendSimpleEmail(String to,String subject,String text){
        // 定制纯文本邮件信息SimpleMailMessage
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(from);
        message.setTo(to);
        message.setSubject(subject);
        message.setText(text);
        try {
            // 发送邮件
            mailSender.send(message);
            System.out.println("纯文本邮件发送成功");
        } catch (MailException e) {
            System.out.println("纯文本邮件发送失败 "+e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * 发送复杂邮件（包括静态资源和附件）
     * @param to           收件人地址
     * @param text         邮件内容
     */
    public String sendComplexEmail(String to,String text){
        // 定制复杂邮件信息MimeMessage
        MimeMessage message = mailSender.createMimeMessage();
        try {
            // 使用MimeMessageHelper帮助类，并设置multipart多部件使用为true
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setFrom(from);
            helper.setTo(to);
            helper.setSubject("超音乐验证码");
            helper.setText(text, true);
            // 发送邮件
            mailSender.send(message);
            return "发送成功";
        } catch (MessagingException e) {
            e.printStackTrace();
            return "发送失败";
        }
    }
}
