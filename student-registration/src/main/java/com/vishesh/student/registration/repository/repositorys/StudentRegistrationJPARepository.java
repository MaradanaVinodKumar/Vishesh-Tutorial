package com.vishesh.student.registration.repository.repositorys;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.vishesh.student.registration.repository.StudentRegistration;

public interface StudentRegistrationJPARepository extends JpaRepository<StudentRegistration, BigDecimal>{
	@Query( nativeQuery = true ,value = "SELECT phone_number FROM student_registration")
    List<String> getPhoneNumbers();
	@Query( nativeQuery = true ,value = "SELECT email_id FROM student_registration")
	List<String> getEmailIds();
}