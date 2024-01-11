package com.vishesh.student.registration.otpService;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import com.vishesh.student.registration.configuration.TwilioConfig;
import com.vishesh.student.registration.dto.OtpRequest;
import com.vishesh.student.registration.dto.OtpResponseDto;
import com.vishesh.student.registration.dto.OtpStatus;
import com.vishesh.student.registration.dto.OtpValidationRequest;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class SmsService {

	
	@Autowired
	private TwilioConfig twilioConfig;
    Map<String, String> otpMap = new HashMap<>();
	public OtpResponseDto sendSMS(OtpRequest otpRequest) {
		OtpResponseDto otpResponseDto = null;
		try {
			Twilio.init(twilioConfig.getAccountSid(), twilioConfig.getAuthToken());
			PhoneNumber to = new PhoneNumber(otpRequest.getPhoneNumber());//to
			PhoneNumber from = new PhoneNumber(twilioConfig.getPhoneNumber()); // from
			String otp = generateOTP();
			String otpMessage = "otp"+otp;
			
			Message message = Message
			        .creator(to, from,
			                otpMessage)
			        .create();
			otpMap.put(otpRequest.getUsername(), otp);
			otpResponseDto = new OtpResponseDto(OtpStatus.DELIVERED, otpMessage);
//			SmsService otps=new SmsService();
			System.out.println("start counting");
			setTimeout(()->{otpMap.clear();System.out.println("OTP expires");},90000);
		} catch (Exception e) {
//			e.printStackTrace();
			System.out.println(e.getMessage());
			otpResponseDto = new OtpResponseDto(OtpStatus.FAILED, e.getMessage());
		}
		return otpResponseDto;
	}
	
	public boolean validateOtp(OtpValidationRequest otpValidationRequest) {
		Set<String> keys = otpMap.keySet();
		String username = null;
		for(String key : keys)
			username = key;
		System.out.println(otpValidationRequest.getOtpNumber()+".equals("+otpMap.get(username)+")");
        if (otpValidationRequest.getOtpNumber().equals(otpMap.get(username))) {
            //otpMap.remove(username,otpValidationRequest.getOtpNumber());
            return true;
        } else {
            return false;
        }
	}
	
	private String generateOTP() {
        return new DecimalFormat("0000")
                .format(new Random().nextInt(9999));
    }
	
	

	public static void setTimeout(Runnable runnable, long delay){
	        new Thread(() -> {
	            try {
	                Thread.sleep(delay);
	                runnable.run();
	            } catch (InterruptedException e) {
	                System.err.println("Failed to execute setTimeout task: " + e.getMessage());
	            }
	        }).start();
	   }
	
	
}