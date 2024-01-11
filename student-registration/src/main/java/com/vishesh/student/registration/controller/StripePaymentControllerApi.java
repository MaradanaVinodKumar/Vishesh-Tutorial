package com.vishesh.student.registration.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.web.bind.annotation.RestController;

import com.vishesh.student.registration.dto.CreatePayment;
import com.vishesh.student.registration.dto.CreatePaymentResponse;

import com.vishesh.student.registration.service.StripeGatewayService;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
public class StripePaymentControllerApi {
	@Value("${stripe.apikey}")
	String stipeKey;
	
	@Autowired
	StripeGatewayService stripeGatewayService;
 @PostMapping("/create-payment-intent")
 public CreatePaymentResponse createPaymentIntent(@RequestBody CreatePayment CreatPayment ) {
	 	 
	 return stripeGatewayService.pamentMethodCreat(CreatPayment);
 }
}
