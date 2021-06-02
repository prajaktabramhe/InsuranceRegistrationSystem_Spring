package com.bridgelabz.imp.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.bridgelabz.imp.util.Response;

import lombok.extern.slf4j.Slf4j;

@ControllerAdvice
@Slf4j
public class UserExceptionHandler 
{
	@ExceptionHandler(Exception.class)
	public ResponseEntity<Response> handleException(Exception e)
	{
		log.error(e.getMessage(), e);
		UserException he = new UserException(100, e.getMessage());
		return new ResponseEntity<>(he.getErrorResponse(), HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
