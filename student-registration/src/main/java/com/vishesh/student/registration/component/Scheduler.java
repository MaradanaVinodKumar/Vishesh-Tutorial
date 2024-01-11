package com.vishesh.student.registration.component;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.vishesh.student.registration.service.StudentScheduleService;

@Component
public class Scheduler {
	@Autowired
	StudentScheduleService studentScheduleService;
	
	@Scheduled(cron = "0 43 14 * * *")
	public void sendStudentScheduleSms() {
		
	        System.out.println("Running daily task at 6:30 ...");
	        studentScheduleService.StudentScheduleDetailsSend();
	        
	}
	

}
