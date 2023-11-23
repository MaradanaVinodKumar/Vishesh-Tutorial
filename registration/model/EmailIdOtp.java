package com.vishesh.student.registration.model;

import org.springframework.context.annotation.Configuration;

@Configuration
public class EmailIdOtp {
	public String emailId;
	public String otpValue;
	
	public String getEmailId() {
		return this.emailId;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	public String getOtpValue() {
		return this.otpValue;
	}
	public void setOtpValue(String otpValue) {
		this.otpValue = otpValue;
	}
}
