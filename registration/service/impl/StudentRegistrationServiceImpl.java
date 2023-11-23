package com.vishesh.student.registration.service.impl;

import java.math.BigDecimal;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vishesh.student.registration.model.EmailIdPassword;
import com.vishesh.student.registration.model.ForgetEmail;
import com.vishesh.student.registration.model.ForgetPhone;
import com.vishesh.student.registration.model.PhoneNumberPassword;
import com.vishesh.student.registration.model.Student;
import com.vishesh.student.registration.model.StudentDetails;
import com.vishesh.student.registration.model.UserPasswordDetails;
import com.vishesh.student.registration.repository.StudentRegistration;
import com.vishesh.student.registration.repository.StudentRegistrationRepository;
import com.vishesh.student.registration.repository.UserPasswordStorage;
import com.vishesh.student.registration.repository.UserPasswordStorageRepository;
import com.vishesh.student.registration.service.StudentRegistrationService;
@Service
public class StudentRegistrationServiceImpl implements StudentRegistrationService {
 @Autowired
StudentRegistrationRepository studentRegistrationRepository;
 @Autowired
 UserPasswordStorageRepository userPasswordStorageRepository;

	@Override
	public Student processStudentRegistration(Student student) {
		StudentRegistration studentRegistration = new StudentRegistration();
		studentRegistration.setFirstName(student.getFirstName());
		studentRegistration.setLastName(student.getLastName());
		studentRegistration.setSchoolName(student.getSchoolName());
		studentRegistration.setPhoneNumber(student.getPhoneNumber());
		studentRegistration.setEmail(student.getEmailId());
		studentRegistration.setClassName(student.getClassName());
		studentRegistration.setPassword(student.getPassword());
		studentRegistrationRepository.save(studentRegistration);
		return null;
	}
	
	@Override
	public Student fetchStudentDetails(Long id) {
		// TODO Auto-generated method stub
	
		Optional<StudentRegistration> studentDetails= studentRegistrationRepository.findById(new BigDecimal(id));
		Student student=null;
		if(studentDetails.isPresent())
		{
			student=new Student();
			StudentRegistration studentResgistration=studentDetails.get();
			student.setFirstName(studentResgistration.getFirstName());
			student.setLastName(studentResgistration.getLastName());
			student.setClassName(studentResgistration.getSchoolName());
			student.setSchoolName(studentResgistration.getSchoolName());
			student.setPhoneNumber(String.valueOf(studentResgistration.getPhoneNumber()));
			student.setEmailId(studentResgistration.getEmail());
		}
		else {
			throw new Error("no record found!");
		}
		return student;
		
	}

	@Override
	public boolean phoneNumberExist(String phoneNumber){    //exist
		// TODO Auto-generated method stub
		
		Iterable<StudentRegistration> studentDetailsByPhoneNumber=studentRegistrationRepository.findAll();
		for(StudentRegistration persion:studentDetailsByPhoneNumber)
		{
//			System.out.println(persion.getPhoneNumber());
			if(persion.getPhoneNumber().equals(phoneNumber))
			{
//				System.out.println("eqals :"+persion.getPhoneNumber());
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean emailIdExist(String emailId) {
		// TODO Auto-generated method stub
		Iterable<StudentRegistration> studentDetailsByPhoneNumber=studentRegistrationRepository.findAll();
		for(StudentRegistration persion:studentDetailsByPhoneNumber)
		{
//			System.out.println(persion.getPhoneNumber());
			if(persion.getEmail().equals(emailId))
			{
//				System.out.println("eqals :"+persion.getPhoneNumber());
				return true;
			}
		}
		return false;
	}

	@Override
	public void storeUserPasswordDetails(UserPasswordDetails userPasswordDetails) {
		// TODO Auto-generated method stub
		UserPasswordStorage userPasswordStorage = new UserPasswordStorage();
		userPasswordStorage.setPhone_number(userPasswordDetails.getPhone());
		userPasswordStorage.setEmail_id(userPasswordDetails.getEmail());
		userPasswordStorage.setPassword(userPasswordDetails.getPassword());
		userPasswordStorageRepository.save(userPasswordStorage);
	}

	@Override
	public boolean PhoneNumberPasswordValidation(PhoneNumberPassword phoneNumberPassword) {
		// TODO Auto-generated method stub
		
		Iterable<StudentRegistration> userPasswordStorage=studentRegistrationRepository.findAll();
		for(StudentRegistration user:userPasswordStorage)
		{
//			System.out.println(persion.getPhoneNumber());
			if(user.getPhoneNumber().equals(phoneNumberPassword.phoneNumber))
			{
				if(user.getPassword().equals(phoneNumberPassword.password))
				{
					return true;
				}
				else
				{
					return false;
				}
//				System.out.println("eqals :"+persion.getPhoneNumber());
			}
		}
		return false;
		
	}

	@Override
	public boolean emailIdPasswordValidation(EmailIdPassword emailIdPassword) {
		// TODO Auto-generated method stub
		Iterable<StudentRegistration> userPasswordStorage=studentRegistrationRepository.findAll();
		for(StudentRegistration user:userPasswordStorage)
		{
//			System.out.println(persion.getPhoneNumber());
			if(user.getEmail().equals(emailIdPassword.getEmailId()))
			{
				if(user.getPassword().equals(emailIdPassword.getPassword()))
				{
					return true;
				}
				else
				{
					return false;
				}
//				System.out.println("eqals :"+persion.getPhoneNumber());
			}
		}
		return false;
	}

	@Override
	public void updateNewPasswordByPhoneNumber(ForgetPhone forgetPhone) {
		// TODO Auto-generated method stub
		Iterable<StudentRegistration> userPasswordStorage=studentRegistrationRepository.findAll();
		for(StudentRegistration user:userPasswordStorage)
		{
//			System.out.println(persion.getPhoneNumber());
			if(user.getPhoneNumber().equals(forgetPhone.getPhoneNumber()))
			{
				StudentRegistration persion= studentRegistrationRepository.findById(user.getStudentId()).get();
				persion.setPassword(forgetPhone.getNewPassword());
				studentRegistrationRepository.save(persion);
//				System.out.println("eqals :"+persion.getPhoneNumber());
			}
		}
	}
	@Override
	public void updateNewPasswordByEmail(ForgetEmail forgetemailId) {
		// TODO Auto-generated method stub
		Iterable<StudentRegistration> userPasswordStorage=studentRegistrationRepository.findAll();
		for(StudentRegistration user:userPasswordStorage)
		{
//			System.out.println(persion.getPhoneNumber());
			if(user.getEmail().equals(forgetemailId.getEmailId()))
			{
				StudentRegistration persion= studentRegistrationRepository.findById(user.getStudentId()).get();
				persion.setPassword(forgetemailId.getNewPassword());
				studentRegistrationRepository.save(persion);
//				System.out.println("eqals :"+persion.getPhoneNumber());
			}
		}
	}

	@Override
	public StudentDetails studentDetailsGet(String userName) {
		// TODO Auto-generated method stub
		System.out.println("user name :"+userName);
		Iterable<StudentRegistration> studentDetatils=studentRegistrationRepository.findAll();
		if(userName.matches("^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@[a-zA-Z0-9-]+(?:\\.[a-zA-Z0-9-]+)*$"))
		{
			for(StudentRegistration student:studentDetatils)
			{
	//			System.out.println(persion.getPhoneNumber());
				System.out.println(student.getPhoneNumber()+"<-->"+userName);
				if(student.getEmail().equals(userName))
				{
					StudentDetails details=new StudentDetails();
					details.setName(student.getFirstName()+" "+student.getLastName());
					details.setPhoneNumber(student.getPhoneNumber());
					details.setEmailId(student.getEmail());
					details.setClassName(student.getClassName());
					details.setSchoolName(student.getSchoolName());
					
					return details;
	//				System.out.println("eqals :"+persion.getPhoneNumber());
				}
			}
		}
		else if(userName.matches("^[6-9][0-9]{9}$"))
		{
			for(StudentRegistration student:studentDetatils)
			{
	//			System.out.println(persion.getPhoneNumber());
				System.out.println(student.getPhoneNumber()+"<-->"+userName);
				if(student.getPhoneNumber().equals(userName))
				{
					StudentDetails details=new StudentDetails();
					details.setName(student.getFirstName()+" "+student.getLastName());
					details.setPhoneNumber(student.getPhoneNumber());
					details.setEmailId(student.getEmail());
					details.setClassName(student.getClassName());
					details.setSchoolName(student.getSchoolName());
					
					return details;
	//				System.out.println("eqals :"+persion.getPhoneNumber());
				}
			}
		}
		else
		{
			throw new Error("user is not found");
		}
		return null;
		
	}
}
