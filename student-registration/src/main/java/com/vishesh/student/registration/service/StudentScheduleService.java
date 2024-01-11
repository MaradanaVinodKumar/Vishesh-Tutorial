package com.vishesh.student.registration.service;

import java.util.List;

import com.vishesh.student.registration.model.ScheduleDetails;

public interface StudentScheduleService {

	public  List<ScheduleDetails> Schedules();
	public void StudentScheduleDetailsSend();
	public List<ScheduleDetails> todaySchedules();

}
