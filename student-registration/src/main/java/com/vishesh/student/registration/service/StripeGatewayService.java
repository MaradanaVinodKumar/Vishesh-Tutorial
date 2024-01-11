package com.vishesh.student.registration.service;

import com.vishesh.student.registration.dto.CreatePayment;
import com.vishesh.student.registration.dto.CreatePaymentResponse;

public interface StripeGatewayService {

	CreatePaymentResponse pamentMethodCreat(CreatePayment createPayment);
	public String CreatCustemer();
	
	public String CreatPaymentMethod();
	
}
