package com.sph.util.service;

import com.sph.util.dto.ResponseDto;

public class CommonUtils {
	
	
	public static final String Product_Reserved="Product_Reserved";
	public static final String Product_Released="Product_Released";
	public static final String Product_Not_Available="Product_Not_Available";
	public static final String Product_Added="Product_Added";
	public static final String Product_Updated="Product_Updated";
	public static final String Product_Cache="Product_Cache";
	
	public static ResponseDto<Object> prepareResponse(String message,Object Data,int status){
		ResponseDto<Object> response=new ResponseDto<>();
		response.setMessage(message);
		response.setData(Data);
		response.setStatus(status);
		return response;
	}

}
