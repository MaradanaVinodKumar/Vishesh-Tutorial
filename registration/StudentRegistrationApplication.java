package com.vishesh.student.registration;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.boot.context.properties.EnableConfigurationProperties;


import com.twilio.Twilio;
import com.vishesh.student.registration.configuration.TwilioConfig;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import jakarta.annotation.PostConstruct;

@SpringBootApplication
@OpenAPIDefinition
@EnableConfigurationProperties
public class StudentRegistrationApplication {
	@Autowired
	private TwilioConfig twilioConfiguration;
	

	
	@PostConstruct
	public void setup() {
		Twilio.init(twilioConfiguration.getAccountSid(), twilioConfiguration.getAuthToken());
	}
	
	
	public static void main(String[] args) {
		SpringApplication.run(StudentRegistrationApplication.class, args);
	}
	


}
