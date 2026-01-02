package com.sph.owner.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.sph.util.dto.ProductDTO;
import com.sph.util.dto.ResponseDto;

import io.github.resilience4j.retry.annotation.Retry;
import io.github.resilience4j.timelimiter.annotation.TimeLimiter;

@FeignClient(name = "ProductService",fallbackFactory =  ProductClientFallbackFactory.class)
@Retry(name="ProductService")
public interface ProductClient {
	
	
	@PostMapping(value ="/addProduct")
	ResponseDto<Object> addProduct( @RequestBody ProductDTO productDTO);

}
