package com.vishesh.student.registration.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vishesh.student.registration.model.EmailIdPassword;
import com.vishesh.student.registration.model.ForgetEmail;
import com.vishesh.student.registration.model.ForgetPhone;
import com.vishesh.student.registration.model.PhoneNumberPassword;
import com.vishesh.student.registration.model.Student;
import com.vishesh.student.registration.model.StudentDetails;
import com.vishesh.student.registration.model.Value;
import com.vishesh.student.registration.model.UserPasswordDetails;
import com.vishesh.student.registration.service.StudentRegistrationService;
import com.vishesh.student.registration.util.OtpValidation;
import com.vishesh.student.registration.util.StudentRegistrationFieldValidation;
import com.vishesh.student.registration.util.StudentValidationUtil;



@RestController
@RequestMapping ("v1/student")
public class StudentRegistraionController {
	@Autowired
	StudentValidationUtil studentValidationUtil;
	
	@Autowired
	StudentRegistrationService studentRegistrationService;
	
	@Autowired
	OtpValidation otpValidation;
	

	@CrossOrigin
	@PostMapping(value = "/register")//, consumes= "application/json", produces= "application/json"
	public Student studentRegistration(@RequestBody Student student) throws Throwable {
		List<String> errorMessage = studentValidationUtil.validateStudentBody(student);
		if(!CollectionUtils.isEmpty(errorMessage)) {
			errorMessage.forEach(error -> {
				throw new Error(error);
			});
		}
	
		if(!otpValidation.isPhoneOtp())
		{
			throw new Error("phone otp is required");
		}
		if(!otpValidation.isEmailotp()) {
			throw new Error("Email otp is required");
		}
		studentRegistrationService.processStudentRegistration(student);
		return student;
	}
	
/*	@CrossOrigin
	@PostMapping(value="/phoneNumberOtpValidation")
	public boolean phoneNumberAndOtp(@RequestBody PhoneNumberOtp phoneNumberOtp){
		otpValidation.validatePhoneNumberOtp(phoneNumberOtp);
		if(otpValidation.isPhoneOtp())
		{
			return true;
		}
		return false;
	}
	
	@CrossOrigin
	@PostMapping(value="/EmailIdOtpValidation")
	public boolean EmailIdAndOtp(@RequestBody EmailIdOtp emailIdOtp){
		otpValidation.validateEmailIdOtp(emailIdOtp);
		if(otpValidation.isEmailotp())
		{
			return true;
		}
		else
		{
			return false;
		}
	}*/
	
	@Autowired
	StudentRegistrationFieldValidation studentRegistrationFieldValidation ;  
	
	@CrossOrigin
	@PostMapping(value="/phoneNumberValidation")
	public boolean phoneNumberValidation(@RequestBody Value phoneNumber) throws Throwable{
		String error;
		if((error=studentRegistrationFieldValidation.phoneNumberValidation(phoneNumber.getValue()))!="")
		{
			throw new Error(error);
		}
		else
		{
			return true;
		}
	}
	
	@CrossOrigin
	@PostMapping(value="/emailIdValidation")
	public boolean emailIdValidation(@RequestBody Value emailId) throws Throwable{
		String error;
		if((error=studentRegistrationFieldValidation.emailIdValidation(emailId.getValue()))!="")
		{
			throw new Error(error);
		}
		else
		{
			return true;
		}
	}
	
	
	
/*	@Autowired
	OtpValidationForLogin otpValidationForLogin;
	@CrossOrigin
	@PostMapping(value="/login")
	public Login login(@RequestBody Login login) throws Throwable {
		if(true)
		{
			throw new Error("---------------------");
		}
//		otpValidationForLogin.validateType(login).getValue();
		return login;
		
	}*/
	
	
	@CrossOrigin
	@GetMapping(value = "/details/{id}")//, consumes= "application/json", produces= "application/*json" remove it
	public Student studentDetails(@PathVariable("id")Long id) throws Throwable {
		return studentRegistrationService.fetchStudentDetails(id);	
	}
	
	@CrossOrigin
	@PostMapping(value = "/userPasswordDetatils")
	public UserPasswordDetails userPasswordDetatils(@RequestBody UserPasswordDetails userpassDetails)  throws Throwable
	{
		try {
		studentRegistrationService.storeUserPasswordDetails(userpassDetails);
		}catch(Exception e) {
			throw new Error(e.getMessage());
		}
		return userpassDetails;
	}
	
	@CrossOrigin
	@PostMapping(value="/loginPhoneNumberValidation")
	public boolean loginPhoneNumberValidation(@RequestBody Value phoneNumber) throws Throwable{ //$
		String error;
		if((error=studentRegistrationFieldValidation.loginPhoneNumberValidation(phoneNumber.getValue()))!="")
		{
			throw new Error(error);
		}
		else
		{
			return true;
		}
	}
	
	@CrossOrigin
	@PostMapping(value="/loginEmailIdValidation")
	public boolean loginEmailIdValidation(@RequestBody Value emailId) throws Throwable{
		String error;
		if((error=studentRegistrationFieldValidation.loginEmailIdValidation(emailId.getValue()))!="")
		{
			throw new Error(error);
		}
		else
		{
			return true;
		}
	}
	
	@CrossOrigin
	@PostMapping(value="/phonePasswordValidation")
	public boolean phonePasswordValidation(@RequestBody PhoneNumberPassword phoneNumberPassword) {
		return studentRegistrationFieldValidation.PhoneNumberPasswordValidation(phoneNumberPassword);
	}
	
	@CrossOrigin
	@PostMapping(value="/emailPasswordValidation")
	public boolean emailPasswordValidation(@RequestBody EmailIdPassword emailIdPassword) {
		
		return studentRegistrationFieldValidation.emailIdPasswordValidation(emailIdPassword);
	}
	
	
	@CrossOrigin
	@PostMapping(value="/phoneNumberForgotPassword")
	public boolean phoneForgetPassword(@RequestBody ForgetPhone forgetPhone) {
		
		try {
			studentRegistrationService.updateNewPasswordByPhoneNumber(forgetPhone);
			}catch(Exception e) {
				throw new Error(e.getMessage());
			}
		return true;
	}
	
	@CrossOrigin
	@PostMapping(value="/emailIdForgotPassword")
	public boolean emailForgetPassword(@RequestBody ForgetEmail forgetEmailId) {
		
		try {
			studentRegistrationService.updateNewPasswordByEmail(forgetEmailId);
			}catch(Exception e) {
				throw new Error(e.getMessage());
			}
		return true;
	}
	
	@CrossOrigin
	@PostMapping(value="/studentDetails")
	public StudentDetails studentDetailsGet(@RequestBody Value userType) {
		
		return studentRegistrationService.studentDetailsGet(userType.getValue());
	}
}

