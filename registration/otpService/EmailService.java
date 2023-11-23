package com.vishesh.student.registration.otpService;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
@Service
public class EmailService{

    private final JavaMailSender mailSender;

    public EmailService(JavaMailSender mailSender)
    {
        this.mailSender = mailSender;
    }

    public boolean sendEmail(String toEmail, String subject, String body)
    {
        try {
    	SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("vinodkumarmaradana2514@gmail.com");
        message.setTo(toEmail);
        message.setSubject(subject);
        message.setText(body);
        mailSender.send(message);
        return true;
        }catch (Exception e) {
        	return false;
        }
    }
    
}
