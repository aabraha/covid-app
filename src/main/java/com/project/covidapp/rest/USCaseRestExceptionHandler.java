package com.project.covidapp.rest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.project.covidapp.model.USCaseErrorResponse;

@ControllerAdvice
public class USCaseRestExceptionHandler {

	@ExceptionHandler
	public ResponseEntity<USCaseErrorResponse> exceptionHandle(USCaseNotFoundException exc){
		
		USCaseErrorResponse error = new USCaseErrorResponse( 
				HttpStatus.NOT_FOUND.value(),
				exc.getMessage(),
				System.currentTimeMillis());
		
		return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
		
	}
	
	// handling the rest exceptions
	@ExceptionHandler
	public ResponseEntity<USCaseErrorResponse> exceptionHandle(Exception exc){
		
		USCaseErrorResponse error = new USCaseErrorResponse( 
				HttpStatus.BAD_REQUEST.value(),
				exc.getMessage(),
				System.currentTimeMillis());
		
		return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
	}

}
