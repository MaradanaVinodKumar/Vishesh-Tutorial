package com.vishesh.student.registration.exceptions;


import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionsHandler {
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<ResponseBody> handleResourceNotFoundException(ResourceNotFoundException exception){
		ResponseBody errorBody=new ResponseBody(HttpStatus.INTERNAL_SERVER_ERROR.value(),exception.getMessage());
		return new ResponseEntity<ResponseBody>(errorBody, HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
