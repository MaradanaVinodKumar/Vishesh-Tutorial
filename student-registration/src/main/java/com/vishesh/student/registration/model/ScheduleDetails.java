package com.vishesh.student.registration.model;

public class ScheduleDetails {
	private String scheduleDate;
	private String scheduleTime;
	private String teacherName;
	private String subjectName;
	private String classDuration;
	public String getScheduleDate() {
		return scheduleDate;
	}
	public void setScheduleDate(String scheduleDate) {
		this.scheduleDate = scheduleDate;
	}
	public String getScheduleTime() {
		return scheduleTime;
	}
	public void setScheduleTime(String scheduleTime) {
		this.scheduleTime = scheduleTime;
	}
	public String getTeacherName() {
		return teacherName;
	}
	public void setTeacherName(String teacherName) {
		this.teacherName = teacherName;
	}
	public String getClassDuration() {
		return classDuration;
	}
	public void setClassDuration(String classDuration) {
		this.classDuration = classDuration;
	}
	public String getSubjectName() {
		return subjectName;
	}
	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}
	
}
