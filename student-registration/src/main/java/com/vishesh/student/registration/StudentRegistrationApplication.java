package com.vishesh.student.registration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;

import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.PaymentMethod;
import com.stripe.param.PaymentMethodAttachParams;
import com.stripe.param.PaymentMethodCreateParams;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import jakarta.annotation.PostConstruct;

@SpringBootApplication
@OpenAPIDefinition
@EnableConfigurationProperties
@EnableScheduling
@EnableJpaRepositories(basePackageClasses = com.vishesh.student.registration.repository.repositorys.StudentRegistrationJPARepository.class)
public class StudentRegistrationApplication {
	@Value("${stripe.apikey}")
	public String stripeApikey;
	@PostConstruct
	public void setup() {
		 Stripe.apiKey=stripeApikey;
	}
	public static void main(String[] args) {
		SpringApplication.run(StudentRegistrationApplication.class, args);
	}
}
