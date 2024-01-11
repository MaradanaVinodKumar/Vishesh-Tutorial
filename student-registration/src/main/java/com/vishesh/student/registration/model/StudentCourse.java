package com.vishesh.student.registration.model;

public class StudentCourse {
	private String courseId;
	private String courseName;
	private String courseRating;
	private String coursePrice;
	private String courseDetails;
	private String courseActiveLearners;
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
	public String getCourseId() {
		return courseId;
	}
	public void setCourseId(String courseId) {
		this.courseId = courseId;
	}
	private String courseLanguage;
}
