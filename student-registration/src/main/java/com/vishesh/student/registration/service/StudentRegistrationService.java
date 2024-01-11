package com.vishesh.student.registration.service;


import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

import com.vishesh.student.registration.exceptions.ResponseBody;
import com.vishesh.student.registration.model.EmailIdPassword;
import com.vishesh.student.registration.model.ForgetEmail;
import com.vishesh.student.registration.model.ForgetPhone;
import com.vishesh.student.registration.model.PhoneNumberPassword;
import com.vishesh.student.registration.model.Student;
import com.vishesh.student.registration.model.StudentDetails;
import com.vishesh.student.registration.model.UserPasswordDetails;

public interface StudentRegistrationService {
public Student processStudentRegistration(Student student);
public void storeUserPasswordDetails(UserPasswordDetails userPasswordDetails);

public Student fetchStudentDetails(Long id);
public boolean phoneNumberExist(String phoneNumber);
public boolean emailIdExist(String emailId);
public boolean PhoneNumberPasswordValidation(PhoneNumberPassword phoneNumberPassword);
public boolean emailIdPasswordValidation(EmailIdPassword emailIdPassword);
public void updateNewPasswordByPhoneNumber(ForgetPhone forgetPhone);
public void updateNewPasswordByEmail(ForgetEmail forgetemailId);
public StudentDetails studentDetailsGet(String username);
public String uploadImage(MultipartFile file, String userId);
public ResponseBody extractExcelFile(MultipartFile excelFile);
public Map<String, Object> extractTextFile(MultipartFile textFile);
}
