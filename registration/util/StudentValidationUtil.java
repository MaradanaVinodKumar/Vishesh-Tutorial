package com.vishesh.student.registration.util;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.vishesh.student.registration.model.Student;

import io.micrometer.common.util.StringUtils;

@Component
public class StudentValidationUtil {

	
	@Autowired
	StudentRegistrationFieldValidation studentRegistrationFieldValidation;
	public List<String> validateStudentBody(Student student) {
		List<String> errorMessage = new ArrayList<>();

		System.out.println(StringUtils.isEmpty(student.getFirstName()));
		
		if (StringUtils.isEmpty(student.getFirstName())){
			errorMessage.add("First name is required");
		}
		if (StringUtils.isEmpty(student.getLastName())) {
			errorMessage.add("Last name is required");
		}
		if (StringUtils.isEmpty(student.getClassName())) {
			errorMessage.add("College name is required");
		}
		if (StringUtils.isEmpty(String.valueOf(student.getPhoneNumber()))){
			errorMessage.add("Phone Number is required");
		}
		else {
			//System.out.println("it's com to the student validation util and aded : "+studentRegistrationFieldValidation.phoneNumberValidation(student.getPhoneNumber()));
			if(!StringUtils.isEmpty(studentRegistrationFieldValidation.phoneNumberValidation(student.getPhoneNumber())))
			{
				errorMessage.add(studentRegistrationFieldValidation.phoneNumberValidation(student.getPhoneNumber()));
			}
		}
		
		if (StringUtils.isEmpty(student.getEmailId())) {
			errorMessage.add("Email Is Required");
		}
		else {
			if(!StringUtils.isEmpty(studentRegistrationFieldValidation.emailIdValidation(student.getEmailId())))
			{
				errorMessage.add(studentRegistrationFieldValidation.emailIdValidation(student.getEmailId()));
			}
		}
	return errorMessage;
	}

}