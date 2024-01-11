package com.vishesh.student.registration.exceptions;

import java.time.LocalDateTime;


public class ResponseBody {
	

	private int status;
	private String message;
	private LocalDateTime timestamp;
	
	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public ResponseBody(int status,String message) {
        this.timestamp = LocalDateTime.now();
        this.status=status;
        this.message = message;

    }

	public LocalDateTime getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(LocalDateTime timestamp) {
		this.timestamp = timestamp;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}

}
