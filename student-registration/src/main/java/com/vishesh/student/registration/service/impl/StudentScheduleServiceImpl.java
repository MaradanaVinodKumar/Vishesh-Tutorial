package com.vishesh.student.registration.service.impl;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import com.twilio.Twilio;
import com.twilio.type.PhoneNumber;
import com.vishesh.student.registration.configuration.TwilioConfigTwo;
import com.vishesh.student.registration.dto.OtpResponseDto;
import com.vishesh.student.registration.dto.OtpStatus;
import com.vishesh.student.registration.model.ScheduleDetails;
import com.twilio.rest.api.v2010.account.Message;
import com.vishesh.student.registration.repository.StudentSchedule;
import com.vishesh.student.registration.repository.repositorys.StudentRegistrationJPARepository;
import com.vishesh.student.registration.repository.repositorys.StudentScheduleJPARepository;
import com.vishesh.student.registration.service.StudentScheduleService;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class StudentScheduleServiceImpl implements StudentScheduleService{
	@Autowired
	StudentScheduleJPARepository studentScheduleRepository;
	@Autowired
	StudentRegistrationJPARepository studentRegistrationJPARepository; 
	@Autowired
	private TwilioConfigTwo twilioConfigs;
	
	 private final JavaMailSender mailSender;

	 public StudentScheduleServiceImpl(JavaMailSender mailSender)
	 {
	        this.mailSender = mailSender;
	 }
	
	
	@Override
	public List<ScheduleDetails> Schedules() {
		
			Iterable<StudentSchedule> studentSchedule =studentScheduleRepository.findAll();
			List<ScheduleDetails> schedulesDetails = new ArrayList(); 
			for(StudentSchedule details: studentSchedule) {
				ScheduleDetails Schedule=new ScheduleDetails();
				Schedule.setScheduleDate(details.getScheduleDate().toString());
				Schedule.setScheduleTime(details.getScheduleTime().toString());
				Schedule.setTeacherName(details.getTeacherName());
				Schedule.setSubjectName(details.getSubjectName());
				Schedule.setClassDuration(details.getClassDuration());
				
				schedulesDetails.add(Schedule);
			}
			
		return schedulesDetails;
	}

//	 @Autowired
//	 StudentRegistrationRepository studentRegistrationRepository;

	
	
	@Override
	public void StudentScheduleDetailsSend() {
		System.out.println("vinod");
		 long millis=System.currentTimeMillis();  
		 java.sql.Date sqlDate = new java.sql.Date(millis);
		Iterable<StudentSchedule> today=studentScheduleRepository.findByDate(sqlDate);
		System.out.println(sqlDate);
		String message="\nHello! today you have classes in vishesh tutorials \n please join in the class\n\n";
		int classCount=1;
		for(StudentSchedule details:today)
		{
			System.out.println(details.getScheduleDate()+" "+details.getTeacherName());
			message+="Class "+classCount+"\nSubject Name :"+details.getSubjectName()+"\n Teacher Name :"+details.getTeacherName()+"\n Class Duration :"+details.getClassDuration()+"\n Time today :"+details.getScheduleTime()+".sec\n\n";
			classCount++;
		}
		System.out.println(message);
		
//		System.out.println(emailService.sendEmail("vinodkumarmaradana2514@gmail.com", "Vishesh Tutorials", message));
		List<String> phoneNumbers=studentRegistrationJPARepository.getPhoneNumbers();
		List<String> emailIds=studentRegistrationJPARepository.getEmailIds();
		
		
		if(classCount!=1)
		{
			for(String phone:phoneNumbers) {
				System.out.println(phone+" Status : "+sendPhoneMessage(message,"+91"+phone).getStatus());
			}		
			
			for(String emailId:emailIds) {
				System.out.println(emailId+" Status : "+sendEmailMessage(message,emailId));
			}
		}

	
	}
		
	public OtpResponseDto sendPhoneMessage(String message,String number) {
		OtpResponseDto otpResponseDto = null;
		try {
		    Twilio.init(twilioConfigs.getAccountSid(), twilioConfigs.getAuthToken());
		    PhoneNumber to = new PhoneNumber(number);//to
		    PhoneNumber from = new PhoneNumber(twilioConfigs.getPhoneNumber()); // from
		    Message phoneMessage = Message
		            .creator(to, from,
		                    message)
		            .create();
		    System.out.println("send to phone number ");
		    otpResponseDto = new OtpResponseDto(OtpStatus.DELIVERED, message);

		    return otpResponseDto;
		} catch (Exception e) {
		    e.printStackTrace();
		    System.out.println(e.getMessage());
		    otpResponseDto = new OtpResponseDto(OtpStatus.FAILED, e.getMessage());
		    System.out.println("not send to phone number ");
		    return otpResponseDto;
		}
	}
	public String sendEmailMessage(String message,String emailid){

		try {
	    	SimpleMailMessage messages = new SimpleMailMessage();
	        messages.setFrom("vinodkumarmaradana2514@gmail.com");
	        messages.setTo(emailid);
	        messages.setSubject("Vishesh Tutorials");
	        messages.setText(message);
	        mailSender.send(messages);
	        return "Sended";
	       }catch (Exception e) {
	        	return "not Sended";
	       }
	}


	@Override
	public List<ScheduleDetails> todaySchedules() {
		long millis=System.currentTimeMillis();  
		java.sql.Date sqlDate = new java.sql.Date(millis);
		Iterable<StudentSchedule> studentSchedules=studentScheduleRepository.findByDate(sqlDate);
		
		List<ScheduleDetails> schedulesDetails = new ArrayList(); 
		
		for(StudentSchedule details: studentSchedules) {
			ScheduleDetails Schedule=new ScheduleDetails();
			Schedule.setScheduleDate(details.getScheduleDate().toString());
			Schedule.setScheduleTime(details.getScheduleTime().toString());
			Schedule.setTeacherName(details.getTeacherName());
			Schedule.setSubjectName(details.getSubjectName());
			Schedule.setClassDuration(details.getClassDuration());
			schedulesDetails.add(Schedule);
		}
		
	return schedulesDetails;
	}
}

