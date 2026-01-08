package com.sph.user.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.sph.util.dto.ResponseDto;

@RestControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(UserNotFoundException.class)
	public ResponseDto<Object> UserNotFoundExceptionExceptionHandler(UserNotFoundException e) {
		return new ResponseDto<Object>(e.getMessage(),null,HttpStatus.NOT_FOUND.value());
		
	}

}
