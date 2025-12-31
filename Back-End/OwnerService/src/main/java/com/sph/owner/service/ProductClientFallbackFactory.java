package com.sph.owner.service;

import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;

import com.sph.util.dto.ResponseDto;

@Component
public class ProductClientFallbackFactory implements FallbackFactory<ProductClient>{

	@Override
	public ProductClient create(Throwable cause) {
		// TODO Auto-generated method stub
		return productDTO  -> {

           
            System.err.println("ProductService failed: " + cause.getMessage());

            ResponseDto<Object> response = new ResponseDto<>();
            response.setStatus(503);
            response.setMessage(
                "Product Service is unavailable. Please try again later."
            );
            response.setData(null);

            return response;
        };
			
		}
	}


