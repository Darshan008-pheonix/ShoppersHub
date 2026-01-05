package com.sph.book.exception;

import java.time.LocalDateTime;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.sph.util.dto.ResponseDto;

@RestControllerAdvice
public class GlobalExceptionHandler {
	
	
	
	 		@ExceptionHandler(BookingsNotFoundException.class)
	    	public ResponseDto<Object> BookingsNotFoundExceptionExceptionHandler(BookingsNotFoundException e) {
	    		return new ResponseDto<Object>(e.getMessage(),null,HttpStatus.NOT_FOUND.value());
	    		
	    	}
	    
}
