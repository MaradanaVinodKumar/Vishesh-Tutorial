package com.vishesh.student.registration.otpService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vishesh.student.registration.model.Email;
import com.vishesh.student.registration.model.EmailIdOtp;



@Service
public class OtpService{

//	@Autowired
//	MailOtpStore mailOtpStore;
    @Autowired
	EmailIdOtp emailIdOtp;
	private final EmailService emailService;
	
    public OtpService(EmailService emailService) {
        this.emailService = emailService;
    }
    public boolean sendOtpToMail(Email toEmail) {	
    	//MailOtpStore mailOtpStore=new MailOtpStore(); 
    	
    	String otp = OtpGenerator.generateOtp();
//    	mailOtpStore.StoreOtp(otp);
    	
    	emailIdOtp.setOtpValue(otp);
    	String subject = "Your OTP For Verification";
        String body = "Your OTP is: " + otp;
        if(emailService.sendEmail(toEmail.getEmail(), subject, body))
        {
        	SmsService smsService=new SmsService();
        	System.out.println("start timer");
    		smsService.setTimeout(()->{emailIdOtp.setOtpValue("0000"); System.out.println("email otp expired");}, 90000);
        	return true;
        }	
        else
        {
        	return false;
        }  
    }
    
	
//	public void StoreOtp(String otp) {
//		emailIdOtp.setOtpValue(otp);
//		SmsService smsService=new SmsService();
//		smsService.setTimeout(()->{emailIdOtp.setOtpValue("0000");}, 90000);
//	}
	
	public boolean validationEmailOtp(String otp)
	{
		
		System.out.println(emailIdOtp.getOtpValue());
		
		if(emailIdOtp.getOtpValue().equals(otp))
		{
			return true;
		}
		else {
			return false;
		}
		
	}

}