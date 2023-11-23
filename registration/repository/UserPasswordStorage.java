package com.vishesh.student.registration.repository;

import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "user_password_details")
public class UserPasswordStorage {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private BigDecimal studentId;
	@Column(name = "phone_number")
	private String phone_number;
	@Column(name = "email_id ")
	private String email_id;

	@Column(name = "password")
	private String password;

	public BigDecimal getStudentId() {
		return studentId;
	}

	public void setStudentId(BigDecimal studentId) {
		this.studentId = studentId;
	}

	public String getPhone_number() {
		return phone_number;
	}

	public void setPhone_number(String phone_number) {
		this.phone_number = phone_number;
	}

	public String getEmail_id() {
		return email_id;
	}

	public void setEmail_id(String email_id) {
		this.email_id = email_id;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
