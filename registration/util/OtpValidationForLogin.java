package com.vishesh.student.registration.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.vishesh.student.registration.model.Login;
import com.vishesh.student.registration.model.Value;
import com.vishesh.student.registration.service.impl.StudentRegistrationServiceImpl;

@Component
public class OtpValidationForLogin {

	@Autowired
	StudentRegistrationServiceImpl studentRegistrationServiceImpl;
	Value typeString;
	public String validateType(Login login) {
		// TODO Auto-generated method stub
		if(login.getType().matches("^[6-9][0-9]{9}$"))
		{
			if(studentRegistrationServiceImpl.phoneNumberExist(login.getType())) {
				if(login.getOtpValue()=="2023") {
					return "true";
				}
			}
			else
			{
				typeString.setValue("The User Is Not Exist");
			}
		}
		else if(login.getType().matches("^[a-zA-Z0-9+_.-]+@[a-zA-Z0-9.-]+$")) {
			if(studentRegistrationServiceImpl.emailIdExist(login.getType()))
			{
				if(login.getOtpValue()=="2023") {
					typeString.setValue("true");
				}
			}
			else
			{
				typeString.setValue("The User Is Not Exist");
			}
		}
		else {
			typeString.setValue("The User Id Is Invalid");
		}	
		return "jhjhj";
		
	}
	
}
