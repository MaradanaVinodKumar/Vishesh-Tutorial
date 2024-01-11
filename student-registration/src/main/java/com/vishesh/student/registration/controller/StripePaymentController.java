package com.vishesh.student.registration.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StripePaymentController {
	@Value("${stripe.apikey}")
	String stipeKey;
}
