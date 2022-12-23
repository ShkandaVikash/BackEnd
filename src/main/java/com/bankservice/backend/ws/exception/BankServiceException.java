package com.bankservice.backend.ws.exception;


import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class BankServiceException extends ResponseEntityExceptionHandler {
	
	@ExceptionHandler(value = {Exception.class})
	public ResponseEntity<Object> handleGlobalException(Exception exception, WebRequest webRequest) {
		if(exception.getCause() instanceof CustomeError) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
	                .contentType(MediaType.APPLICATION_JSON)
	                .body(exception.getMessage());
		}
		 return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
	                .contentType(MediaType.APPLICATION_JSON)
	                .body(exception.getMessage());
	}
	/*
	 * @ExceptionHandler(value = {CustomeError.class}) public ResponseEntity<Object>
	 * handleCustomeError(CustomeError exception, WebRequest webRequest) {
	 * 
	 * }
	 */
}
