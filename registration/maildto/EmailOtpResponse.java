package com.vishesh.student.registration.maildto;

import java.text.DecimalFormat;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;
import com.vishesh.student.registration.model.EmailOtpRequest;


@Configuration
public class EmailOtpResponse {
//	
//	@Autowired
//	private MailService mailService;
//	
//	@Autowired
//	private EmailOtpRequest emailOtpRequest;
//	
//	@EventListener(ApplicationReadyEvent.class)
//	public void triggerMail() {
//		EmailOtpResponse emailOtpResponse=new EmailOtpResponse();
//		String otp=emailOtpResponse.generateOTP();
//		
//		mailService.sendMail(emailOtpRequest.getEmailId(),otp);
//	}
//	
//	private String generateOTP() {
//        return new DecimalFormat("0000")
//                .format(new Random().nextInt(9999));
//    }
}
