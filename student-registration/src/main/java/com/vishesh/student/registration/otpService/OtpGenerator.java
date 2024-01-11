package com.vishesh.student.registration.otpService;

import java.text.DecimalFormat;
import java.util.Random;

public class OtpGenerator {

//    public static String generateOtp() {
//        int length = 6;
//        String numbers = "0123456789";
//
//        StringBuilder otp = new StringBuilder();
//        for (int i = 0; i < length; i++) {
//            otp.append(numbers.charAt((int) Math.floor(Math.random() * numbers.length())));
//        }
//
//        return otp.toString();
//  
//    	}
    
	static String generateOtp() {
		
  return new DecimalFormat("0000")
          .format(new Random().nextInt(9999));
}
}
