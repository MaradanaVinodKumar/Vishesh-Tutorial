package com.vishesh.student.registration.util;

import org.springframework.stereotype.Component;




@Component
public class OtpValidation {
	
//	public String otpPhone="2023";
//	public String otpEmail="2023";
	private boolean phoneOtp=false;
	private boolean emailotp;
	
	
	public boolean isPhoneOtp() {
		return phoneOtp;
	}
	public void setPhoneOtp(boolean phoneOtp) {
		this.phoneOtp = phoneOtp;
	}
	public boolean isEmailotp() {
		return emailotp;
	}
	public void setEmailotp(boolean emailotp) {
		this.emailotp = emailotp;
	}
	
	





	
/*	public boolean validatePhoneNumberOtp(PhoneNumberOtp phoneNumber) {
		if(phoneNumber.getOtpValue().equals(this.otpPhone)) {
			
			this.setPhoneOtp(true);
			return true;
		}
		else {
			this.setPhoneOtp(false);
			return false;
		}
	}
	public boolean validateEmailIdOtp(EmailIdOtp emailID) {
		
		if(emailID.getOtpValue().equals(this.otpEmail)) {
			this.setEmailotp(true);
			return true;
		}
		else {
			this.setEmailotp(false);
			return false;
		}
	}*/
}
