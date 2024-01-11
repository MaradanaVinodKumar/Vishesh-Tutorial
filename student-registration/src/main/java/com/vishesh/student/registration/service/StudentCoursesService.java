package com.vishesh.student.registration.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import com.vishesh.student.registration.model.StudentCourse;
import com.vishesh.student.registration.model.StudentesCoursesInCartModel;
import com.vishesh.student.registration.repository.StudentesCoursesInCart;

public interface StudentCoursesService {

	public List<StudentCourse> fetchStudentCourses();

	public StudentCourse fetchStudentCourseById(String courseId);

	public List<StudentCourse> fetchStudentCoursesInCartByUserId(String userId);

	public StudentesCoursesInCartModel addToCourseToUserCart(String userId, String user_course_id);
	
	public BigDecimal findUserId(String userName);

	public StudentesCoursesInCart removeCourseInUserCart(String userId, String userCourseId);

	public String studentCourseExistInCart(String userId, String userCourseId);
	
	
	
}