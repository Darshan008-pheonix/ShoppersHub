package com.sph.owner.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.sph.util.dto.ProductDTO;
import com.sph.util.dto.ResponseDto;

@FeignClient(name = "ProductService")
public interface ProductClient {
	
	
	@PostMapping(value ="/addProduct")
	ResponseDto<Object> addProduct( @RequestBody ProductDTO productDTO);

}
