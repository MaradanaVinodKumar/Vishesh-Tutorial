package com.vishesh.student.registration.service.impl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.Customer;
import com.stripe.model.PaymentIntent;
import com.stripe.model.PaymentMethod;
import com.stripe.param.AccountCreateParams.Type;
import com.stripe.param.CustomerCreateParams;
import com.stripe.param.PaymentIntentCreateParams;
import com.stripe.param.PaymentMethodAttachParams;
import com.stripe.param.PaymentMethodCreateParams;
import com.vishesh.student.registration.dto.CreatePayment;
import com.vishesh.student.registration.dto.CreatePaymentResponse;
import com.vishesh.student.registration.service.StripeGatewayService;


@Service
public class StripeGatewayServiceImpl implements StripeGatewayService{

	@Value("${stripe.apikey}")
	String stipeKey;
	@Override
	public CreatePaymentResponse pamentMethodCreat(CreatePayment createPayment){
//		System.out.println(CreatCustemer());
		Stripe.apiKey =stipeKey;
//	      CreatePayment postBody = gson.fromJson(request.body(), CreatePayment.class);
	      PaymentIntentCreateParams params =
	        PaymentIntentCreateParams.builder()
	          .setAmount(890 * 100L)
	          .setCurrency("usd")
	      // In the latest version of the API, specifying the `automatic_payment_methods` parameter is optional because Stripe enables its functionality by default.
	          .setAutomaticPaymentMethods(
	            PaymentIntentCreateParams.AutomaticPaymentMethods
	              .builder()
	              .setEnabled(true)
	              .build()
	          ).setCustomer("cus_PLxGyZhoYCnzMH")
	          .setPaymentMethod("visa")
	          .build();
	      PaymentIntent paymentIntent;
		try {
			paymentIntent = PaymentIntent.create(params);
			return new CreatePaymentResponse(paymentIntent.getClientSecret());
		} catch (StripeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new Error(e);
		}
	      
		
	}
	@Override
	public String CreatCustemer() {
		
////	String pamentMethod=CreatPaymentMethod();
//	Stripe.apiKey = stipeKey;
//	CustomerCreateParams params =
//	  CustomerCreateParams.builder()
//	    .setName("kumar")
//	    .setEmail("jennyrosen@example.com")
//	    .setBalance(5000 *100L)
////	    .setPaymentMethod(pamentMethod)
//	    .setPhone("8074485567")
//	    .build();
//	try {
//		Customer customer = Customer.create(params);
//		System.out.println(customer.getId());
//		return customer.getId();
//	} catch (StripeException e) {
//		// TODO Auto-generated catch block
//		e.printStackTrace();
//	}
		return null;
	}
	
	@Override
	public String CreatPaymentMethod() {
		
//		try {
//			Stripe.apiKey = stipeKey;
//			PaymentMethod paymentMethod = PaymentMethod.create(
//			        PaymentMethodCreateParams.builder()
//			                .setType(PaymentMethodCreateParams.Type.CARD)
//			                .setCard(PaymentMethodCreateParams.CardDetails.builder()
//			                        .setNumber("4242424242424242")
//			                        .setExpMonth(12l)
//			                        .setExpYear(2024l)
//			                        .setCvc("123")
//			                        .build())
//			                .build()
//			);
//			return paymentMethod.getCustomer();
//		} catch (StripeException e) {
//			e.printStackTrace();
//			throw new Error(e);
//		}
		 Stripe.apiKey = stipeKey;

	        try {
	            // Create a payment method
	            PaymentMethod paymentMethod = PaymentMethod.create(
	                    PaymentMethodCreateParams.builder()
	                            .setType(PaymentMethodCreateParams.Type.CARD)
	                            .setCard(PaymentMethodCreateParams.CardDetails.builder()
	                                    .setNumber("4242424242424242")
	                                    .setExpMonth(12l)
	                                    .setExpYear(2024l)
	                                    .setCvc("123")
	                                    .build())
	                            .build()
	            );

	            // Attach the payment method to the customer
	            PaymentMethod attachedPaymentMethod = paymentMethod.attach(
	                    PaymentMethodAttachParams.builder()
	                            .setCustomer("cus_PLxGyZhoYCnzMH") // Replace with the actual customer ID
	                            .build()
	            );

	            // 'attachedPaymentMethod' object now contains information about the attached payment method
	            System.out.println("Payment method attached: " + attachedPaymentMethod.getId());
	        } catch (StripeException e) {
	            e.printStackTrace();
	        }
		return null;
	}

}
