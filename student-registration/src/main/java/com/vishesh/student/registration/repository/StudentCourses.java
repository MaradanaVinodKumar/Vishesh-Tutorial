package com.vishesh.student.registration.repository;

import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="student_courses")
public class StudentCourses {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="course_id")
	private BigDecimal courseId;
	@Column(name="course_name")
	private String courseName;
	@Column(name="course_rating")
	private String courseRating;
	public BigDecimal getCourseId() {
		return courseId;
	}
	public void setCourseId(BigDecimal courseId) {
		this.courseId = courseId;
	}
	public String getCourseName() {
		return courseName;
	}
	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}
	public String getCourseRating() {
		return courseRating;
	}
	public void setCourseRating(String courseRating) {
		this.courseRating = courseRating;
	}
	public String getCoursePrice() {
		return coursePrice;
	}
	public void setCoursePrice(String coursePrice) {
		this.coursePrice = coursePrice;
	}
	public String getCourseDetails() {
		return courseDetails;
	}
	public void setCourseDetails(String courseDetails) {
		this.courseDetails = courseDetails;
	}
	public String getCourseActiveLearners() {
		return courseActiveLearners;
	}
	public void setCourseActiveLearners(String courseActiveLearners) {
		this.courseActiveLearners = courseActiveLearners;
	}
	public String getCourseLanguage() {
		return courseLanguage;
	}
	public void setCourseLanguage(String courseLanguage) {
		this.courseLanguage = courseLanguage;
	}
	@Column(name="course_price")
	private String coursePrice;
	@Column(name="course_details")
	private String courseDetails;
	@Column(name="course_active_learners")
	private String courseActiveLearners;
	@Column(name="course_language")
	private String courseLanguage;

}
