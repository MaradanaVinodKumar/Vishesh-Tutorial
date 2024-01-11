package com.vishesh.student.registration.repository;

import java.math.BigDecimal;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table (name = "student_registration")
public class StudentRegistration {
   @Id
   @GeneratedValue (strategy = GenerationType.IDENTITY)
   @Column (name="id")
   private BigDecimal studentId;
	
   @Column (name="first_name")
	private String firstName;
	
	@Column (name="Last_name")
	private String lastName;
	
	@Column (name="class_name")
	private String className;
	
	@Column (name="school_name")
	private String schoolName;
	
	@Column (name="phone_number")
	private String phoneNumber;
	
	@Column (name="email_id")
	private String email;

	@Column (name="password")
	private String password;
	
//	@Column (name="image")
//	private byte[] image;
	
//	public byte[] getImage() {
//		return image;
//	}
//
//	public void setImage(byte[] image) {
//		this.image = image;
//	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public BigDecimal getStudentId() {
		return studentId;
	}

	public void setStudentId(BigDecimal studentId) {
		this.studentId = studentId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public String getSchoolName() {
		return schoolName;
	}

	public void setSchoolName(String schoolName) {
		this.schoolName = schoolName;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}	
}
