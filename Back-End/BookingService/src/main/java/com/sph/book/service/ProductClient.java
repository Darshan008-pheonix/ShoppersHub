package com.sph.book.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.sph.util.dto.ResponseDto;

@FeignClient(name = "ProductService",value = "ProductService")
public interface ProductClient {
	
	
	@GetMapping("/validateProduct")
	ResponseDto<Object> validateProduct(@RequestParam String pid,@RequestParam int qnt );

	
	
	@PatchMapping("/reserveProduct")
	ResponseDto<Object> reserveProduct(@RequestParam String pid,@RequestParam int qnt );
}
