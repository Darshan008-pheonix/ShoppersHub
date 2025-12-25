package com.sph.util.model;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@lombok.Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseDto<Object> {
	
	private String message;
	private Object Data;
	private int status;
	
}
