package com.vishesh.student.registration.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import lombok.Data;

@Configuration
@Component
@ConfigurationProperties(prefix="twilio")
@Data
public class TwilioConfigTwo {
		private String accountSid;
		private String authToken;
		private String phoneNumber;
		public String getAccountSid() {
			return accountSid;
		}
		public void setAccountSid(String accountSid) {
			this.accountSid = accountSid;
		}
		public String getAuthToken() {
			return authToken;
		}
		public void setAuthToken(String authToken) {
			this.authToken = authToken;
		}
		public String getPhoneNumber() {
			return phoneNumber;
		}
		public void setPhoneNumber(String phoneNumber) {
			this.phoneNumber = phoneNumber;
		}
}
