package com.vishesh.student.registration.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vishesh.student.registration.model.ScheduleDetails;
import com.vishesh.student.registration.service.StudentScheduleService;

@RestController
@RequestMapping("v1/student")
public class StudentScheduleController {
	
	@Autowired
	StudentScheduleService studentScheduleService;
	
	@CrossOrigin
	@GetMapping(value="/Schedule")
	public List<ScheduleDetails> schedulesGet() {
		return studentScheduleService.Schedules();
	}
	@CrossOrigin
	@GetMapping(value="/todaySchedules")
	public List<ScheduleDetails> todaySchedulesGet() {
		return studentScheduleService.todaySchedules();
	}
}
