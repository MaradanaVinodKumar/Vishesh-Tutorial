package com.vishesh.student.registration.repository;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Time;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="student_schedule")
public class StudentSchedule {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private BigDecimal scheduleId;
	@Column(name="Schedule_date")
	private Date scheduleDate;
	@Column(name="Schedule_time")
	private Time  scheduleTime;
	@Column(name="Teacher_name")
	private String teacherName;
	@Column(name="Subject_name")
	private String subjectName;
	@Column(name="Class_duration")
	private String classDuration;
	public BigDecimal getScheduleId() {
		return scheduleId;
	}
	public void setScheduleId(BigDecimal scheduleId) {
		this.scheduleId = scheduleId;
	}
	public Date getScheduleDate() {
		return scheduleDate;
	}
	public void setScheduleDate(Date scheduleDate) {
		this.scheduleDate = scheduleDate;
	}
	public Time getScheduleTime() {
		return scheduleTime;
	}
	public void setScheduleTime(Time scheduleTime) {
		this.scheduleTime = scheduleTime;
	}
	public String getTeacherName() {
		return teacherName;
	}
	public void setTeacherName(String teacherName) {
		this.teacherName = teacherName;
	}
	public String getSubjectName() {
		return subjectName;
	}
	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}
	public String getClassDuration() {
		return classDuration;
	}
	public void setClassDuration(String classDuration) {
		this.classDuration = classDuration;
	}
}
