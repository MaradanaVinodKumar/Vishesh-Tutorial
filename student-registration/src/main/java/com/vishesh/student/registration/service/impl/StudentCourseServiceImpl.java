package com.vishesh.student.registration.service.impl;


import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vishesh.student.registration.model.StudentCourse;
import com.vishesh.student.registration.model.StudentesCoursesInCartModel;
import com.vishesh.student.registration.repository.StudentCourses;
import com.vishesh.student.registration.repository.StudentRegistration;
import com.vishesh.student.registration.repository.StudentesCoursesInCart;
import com.vishesh.student.registration.repository.repositorys.StudentCoursesRepository;
import com.vishesh.student.registration.repository.repositorys.StudentRegistrationRepository;
import com.vishesh.student.registration.repository.repositorys.StudentesCoursesInCartRepository;
import com.vishesh.student.registration.service.StudentCoursesService;

@Service
public class StudentCourseServiceImpl implements StudentCoursesService {
	@Autowired
	StudentCoursesRepository  studentCoursesRepository;
	@Autowired
	StudentesCoursesInCartRepository studentesCoursesInCartRepository;
	@Autowired
	StudentRegistrationRepository studentRegistrationRepository;
	@Autowired
	@Override
	public List<StudentCourse> fetchStudentCourses() {
		Iterable<StudentCourses> totalStudentCourses =studentCoursesRepository.findAll();
		List<StudentCourse> studentCourses = new ArrayList();
		
		for(StudentCourses course:totalStudentCourses)
		{
			StudentCourse studentCourse=new  StudentCourse();
			studentCourse.setCourseId(course.getCourseId().toString());
			studentCourse.setCourseName(course.getCourseName());
			studentCourse.setCourseDetails(course.getCourseDetails());
			studentCourse.setCourseActiveLearners(course.getCourseActiveLearners());
			studentCourse.setCourseLanguage(course.getCourseLanguage());
			studentCourse.setCoursePrice(course.getCoursePrice());
			studentCourse.setCourseRating(course.getCourseRating());
			studentCourses.add(studentCourse);
			
		}
		return studentCourses;
		
	}
	@Override
	public StudentCourse fetchStudentCourseById(String courseId) {
		Optional<StudentCourses> studentCourses=studentCoursesRepository.findById( new BigDecimal(courseId));
		StudentCourses Course =studentCourses.get();
		StudentCourse studentCourse=new  StudentCourse();
		studentCourse.setCourseId(Course.getCourseId().toString());
		studentCourse.setCourseName(Course.getCourseName());
		studentCourse.setCourseDetails(Course.getCourseDetails());
		studentCourse.setCourseActiveLearners(Course.getCourseActiveLearners());
		studentCourse.setCourseLanguage(Course.getCourseLanguage());
		studentCourse.setCoursePrice(Course.getCoursePrice());
		studentCourse.setCourseRating(Course.getCourseRating());
		return studentCourse;
	}
	@Override
	public List<StudentCourse> fetchStudentCoursesInCartByUserId(String userName) {
		BigDecimal userId = findUserId(userName);
		//find list of courses by user id
		Iterable<StudentesCoursesInCart> findByUserId =studentesCoursesInCartRepository.findByUserId(userId);
		List<StudentCourse> listOfStudentCourse=new ArrayList();
		for(StudentesCoursesInCart locations:findByUserId) {
//			System.out.println(locations.getUserId()+"  "+locations.getUserCourseId());
			listOfStudentCourse.add(fetchStudentCourseById(locations.getUserCourseId().toString()));
		}
		
		return listOfStudentCourse;
	}
	@Override
	public StudentesCoursesInCartModel addToCourseToUserCart(String userName, String user_course_id) {
		
				BigDecimal userId = findUserId(userName);
		 
				StudentesCoursesInCart studentesCoursesInCart=new StudentesCoursesInCart();
				studentesCoursesInCart.setUserId(userId);
				studentesCoursesInCart.setUserCourseId(new BigDecimal(user_course_id));
				studentesCoursesInCartRepository.save(studentesCoursesInCart);
				StudentesCoursesInCartModel studentesCoursesInCartModel =new StudentesCoursesInCartModel();
				studentesCoursesInCartModel.setUserId(userId.toString());
				studentesCoursesInCartModel.setUuserCourseId(user_course_id);
				return studentesCoursesInCartModel;
	}
	@Override
	public StudentesCoursesInCart removeCourseInUserCart(String userName, String userCourseId) {
		BigDecimal userId = findUserId(userName);
//		studentesCoursesInCartRepository.removeCourse(userId,new BigDecimal(userCourseId));
		studentesCoursesInCartRepository.removeCourse(userId,new BigDecimal(userCourseId));
		return null;
	}
	
	@Override
	public String studentCourseExistInCart(String userName, String userCourseId) {
		BigDecimal userId = findUserId(userName);
		Optional<StudentesCoursesInCart> StudenteCourseInCart = studentesCoursesInCartRepository.studentCourseExistInCart( userId,new BigDecimal(userCourseId));
		if(!StudenteCourseInCart.isPresent())
		{
			return "not existed the course";
		}
		throw new Error("existed the course");
	}
	
	@Override
	public BigDecimal findUserId(String userName) {
		BigDecimal userId = new BigDecimal(0);
		if ((userName.matches("^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@[a-zA-Z0-9-]+(?:\\.[a-zA-Z0-9-]+)*$"))
				&& (!userName.isEmpty())) {
			
			Optional<StudentRegistration> user = studentRegistrationRepository.findByEmail(userName);
			if (user.isPresent()) {
				StudentRegistration userdetails=user.get();
				userId=userdetails.getStudentId();
			}
			else
			{
				throw new Error("email id type user is not found");
			}
			
		}
		else if (userName.matches("^[6-9][0-9]{9}$") && (!userName.isEmpty())) {
			 Optional<StudentRegistration> user = studentRegistrationRepository.findByPhoneNumber(userName);
				if (user.isPresent()) {
					StudentRegistration userdetails=user.get();
					userId=userdetails.getStudentId();
				}
				else
				{
					throw new Error("phone number type user is not found ");
				}
		 }
		return userId;
	}
}
