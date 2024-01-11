package com.vishesh.student.registration.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.vishesh.student.registration.model.StudentCourse;
import com.vishesh.student.registration.model.StudentesCoursesInCartModel;
import com.vishesh.student.registration.repository.StudentesCoursesInCart;
import com.vishesh.student.registration.service.StudentCoursesService;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
@RequestMapping ("v1/StudentCourses")
public class StudentCoursesController {
	@Autowired
	StudentCoursesService studentCoursesService;
	
	@CrossOrigin
	@GetMapping(value = "/Courses")//, consumes= "application/json", produces= "application/*json" remove it
	public List<StudentCourse> studentCourses() {
		return studentCoursesService.fetchStudentCourses();
	}
	
	@CrossOrigin
	@PostMapping(value = "/Courses")//, consumes= "application/json", produces= "application/*json" remove it
	public StudentCourse studentCourse(@RequestParam String courseId) {
		return studentCoursesService.fetchStudentCourseById(courseId);
	}
	
	@CrossOrigin
	@PostMapping(value = "/studentCoursesInCart")//, consumes= "application/json", produces= "application/*json" remove it
	public List<StudentCourse> studentCoursesInCart(@RequestParam String userId) {
		return studentCoursesService.fetchStudentCoursesInCartByUserId(userId);
	}
	
	@CrossOrigin
	@PostMapping(value = "/studentCourseAddToCart")//, consumes= "application/json", produces= "application/*json" remove it
	public StudentesCoursesInCartModel studentCourseAddToCart(@RequestParam String userId,@RequestParam String userCourseId) {
		return studentCoursesService.addToCourseToUserCart(userId,userCourseId);
	}
	
	@CrossOrigin
	@PostMapping(value = "/studentCourseRemoveToCart")//, consumes= "application/json", produces= "application/*json" remove it
	public StudentesCoursesInCart studentCourseRemoveToCart(@RequestParam String userId,@RequestParam String userCourseId) {
		return studentCoursesService.removeCourseInUserCart(userId,userCourseId);
	}
	
	@CrossOrigin
	@PostMapping(value = "/studentCourseExistInCart")//, consumes= "application/json", produces= "application/*json" remove it
	public String studentCourseExistInCart(@RequestParam String userId,@RequestParam String userCourseId) {
		return studentCoursesService.studentCourseExistInCart(userId,userCourseId);
	}
	
	
}
