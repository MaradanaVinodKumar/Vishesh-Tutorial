package com.vishesh.student.registration.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.vishesh.student.registration.model.EmailIdPassword;
import com.vishesh.student.registration.model.PhoneNumberPassword;
import com.vishesh.student.registration.service.StudentRegistrationService;


import io.micrometer.common.util.StringUtils;
@Component
public class StudentRegistrationFieldValidation {
	@Autowired
	StudentRegistrationService studentRegistrationService ;

	public String phoneNumberValidation(String phoneNumber)
	{	
		//studentRegistrationService=new StudentRegistrationServiceImpl();
		String message="";
		if (StringUtils.isEmpty(String.valueOf(phoneNumber))){
			return "Phone Number is required";
		}
		else {
		
			if((phoneNumber.length())==10)
			{
				if(phoneNumber.matches("^[6-9][0-9]{9}$"))
				{	
					if(studentRegistrationService.phoneNumberExist(phoneNumber))
					{
						message="The Phone Number Is Already Exist";
					}
				}
				else
				{
					//validate phone number
					message="The Phone Number Is Invalid";
				}
			}
			else
			{
				message="The Phone Number Contains Onley 10 Digits.";
			}
			System.out.println("it's com to the student validation and returned : "+message);
			
		}
		return message;
	}
	
	public String emailIdValidation(String emailId) {
		String message="";
//		studentRegistrationService=new StudentRegistrationServiceImpl();
		if (StringUtils.isEmpty(emailId)) {
			message="Email Is Required";
		}
		else {
			if((emailId).matches("^[a-zA-Z0-9+_.-]+@[a-zA-Z0-9.-]+$")) {
				
				if(studentRegistrationService.emailIdExist(emailId))
				{
					message="The Email Is Already Exist";
				}
			}
			else
			{
				message="The Email Is Not Currect Pattern";
			}
		}
		return message;
	}
	
	
	public String loginPhoneNumberValidation(String phoneNumber)
	{	
		//studentRegistrationService=new StudentRegistrationServiceImpl();
		String message="";
		if (StringUtils.isEmpty(String.valueOf(phoneNumber))){
			return "Phone Number is required";
		}
		else {
		
			if((phoneNumber.length())==10)
			{
				if(phoneNumber.matches("^[6-9][0-9]{9}$"))
				{	
					if(!studentRegistrationService.phoneNumberExist(phoneNumber))
					{
						message="The Phone Number Is Not Exist";
					}
				}
				else
				{
					//validate phone number
					message="The Phone Number Is Invalid";
				}
			}
			else
			{
				message="The Phone Number Contains Onley 10 Digits.";
			}
			System.out.println("it's com to the student validation and returned : "+message);
			
		}
		return message;
	}
	
	public String loginEmailIdValidation(String emailId) {
		String message="";
//		studentRegistrationService=new StudentRegistrationServiceImpl();
		if (StringUtils.isEmpty(emailId)) {
			message="Email Is Required";
		}
		else {
			if((emailId).matches("^[a-zA-Z0-9+_.-]+@[a-zA-Z0-9.-]+$")) {
				
				if(!studentRegistrationService.emailIdExist(emailId))
				{
					message="The Email Is not Exist";
				}
			}
			else
			{
				message="The Email Is Not Currect Pattern";
			}
		}
		return message;
	}
	
	
	public boolean PhoneNumberPasswordValidation(PhoneNumberPassword phoneNumberPassword ) {
		return studentRegistrationService.PhoneNumberPasswordValidation(phoneNumberPassword);
	}

	public boolean emailIdPasswordValidation(EmailIdPassword emailIdPassword) {
		// TODO Auto-generated method stub
		return studentRegistrationService.emailIdPasswordValidation(emailIdPassword);
	}
}
