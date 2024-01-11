package com.vishesh.student.registration.repository;

import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="studentes_courses_in_cart")
public class StudentesCoursesInCart {
	@Id
	@GeneratedValue(strategy =GenerationType.IDENTITY)
	@Column(name="id")
	private BigDecimal id;
	@Column(name="user_id")
	private BigDecimal userId;
	@Column(name="user_course_id")
	private BigDecimal userCourseId;
	public BigDecimal getId() {
		return id;
	}
	public void setId(BigDecimal id) {
		this.id = id;
	}
	public BigDecimal getUserId() {
		return userId;
	}
	public void setUserId(BigDecimal userId) {
		this.userId = userId;
	}
	public BigDecimal getUserCourseId() {
		return userCourseId;
	}
	public void setUserCourseId(BigDecimal userCourseId) {
		this.userCourseId = userCourseId;
	}
	
}
