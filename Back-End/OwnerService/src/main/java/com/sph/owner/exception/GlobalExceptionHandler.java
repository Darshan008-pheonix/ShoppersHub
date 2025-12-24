package com.sph.owner.exception;

import java.time.LocalDateTime;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
	 @ExceptionHandler(OwnerNotFoundException.class)
	  
	    	public ResponseEntity EmployeeNotFoundExceptionHandler(OwnerNotFoundException e) {
	    		return new ResponseEntity(e.getMessage(),HttpStatus.NOT_FOUND);
	    		
	    	
	    		
	    	}
	    
}
