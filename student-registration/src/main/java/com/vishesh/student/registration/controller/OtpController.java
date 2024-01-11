package com.vishesh.student.registration.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vishesh.student.registration.dto.OtpRequest;
import com.vishesh.student.registration.dto.OtpResponseDto;
import com.vishesh.student.registration.dto.OtpValidationRequest;
import com.vishesh.student.registration.model.Email;
import com.vishesh.student.registration.model.EmailOtpValidation;
import com.vishesh.student.registration.otpService.OtpService;
import com.vishesh.student.registration.otpService.SmsService;
import com.vishesh.student.registration.service.StudentRegistrationService;
import com.vishesh.student.registration.util.OtpValidation;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/otp")
@Slf4j
public class OtpController {

	@Autowired
	private SmsService smsService;
	
	@Autowired
	StudentRegistrationService studentRegistrationService;
	
	@Autowired
	OtpValidation otpValidation;
	
	@GetMapping("/process")
	public String processSMS() {
		return "SMS sent";
	}
	
	private static final Logger logger =LoggerFactory.getLogger(OtpController.class);
	
	@CrossOrigin
	@PostMapping("/sendOtpToPhoneNumber")
	public OtpResponseDto sendOtpToPhoneNumber(@RequestBody OtpRequest otpRequest) {
		logger.info("inside sendOtp :: "+otpRequest.getUsername());
		
		return smsService.sendSMS(otpRequest);
	}
	
	@CrossOrigin
	@PostMapping("/phoneNumberOtpValidate")
    public boolean phoneNumberOtpvalidate(@RequestBody OtpValidationRequest otpValidationRequest) {
		logger.info("inside validateOtp :: "+otpValidationRequest.getUsername()+" "+otpValidationRequest.getOtpNumber());
		boolean response=smsService.validateOtp(otpValidationRequest);
		System.out.println("phone otp validation status :"+response);
		
		if(response)
		{
			otpValidation.setPhoneOtp(true);	
			return true;
		}
		else{
			otpValidation.setPhoneOtp(false);
			return false;
		}
//		System.out.println(studentRegistrationService.studentDetailsGet(otpValidationRequest.getUsername()).getName());
		//return studentRegistrationService.studentDetailsGet(otpValidationRequest.getUsername());
		
    }
	
	private final OtpService otpService;

	public OtpController(OtpService otpService)
	{
	        this.otpService = otpService;
	}
	
	@CrossOrigin
	@PostMapping("/sendOtpToEmail")
	public boolean sendOtp(@RequestBody Email email) {
		
		if(otpService.sendOtpToMail(email))
		{
			return true;
		}
		else {
			return false;
		}
	}
	
	@CrossOrigin
	@PostMapping("/validateOtpForEmail")
	public boolean emailOtpValidation(@RequestBody EmailOtpValidation otp) throws Throwable {
		if(otpService.validationEmailOtp(otp.getOtp()))
		{
			otpValidation.setEmailotp(true);
			return true;
		}
		else{
			otpValidation.setEmailotp(false);
			return false;
		}
	}
		
}