package com.vishesh.student.registration.model;

import org.springframework.context.annotation.Configuration;

@Configuration
public class Email {
	private String email;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
}
