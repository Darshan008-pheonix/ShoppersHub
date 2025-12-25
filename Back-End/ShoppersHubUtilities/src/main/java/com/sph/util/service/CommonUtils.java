package com.sph.util.service;

import com.sph.util.dto.ResponseDto;

public class CommonUtils {
	
	
	public static ResponseDto<Object> prepareResponse(String message,Object Data,int status){
		ResponseDto<Object> response=new ResponseDto<>();
		response.setMessage(message);
		response.setData(Data);
		response.setStatus(status);
		return response;
	}

}
