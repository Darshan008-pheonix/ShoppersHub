package com.sph.book.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

import com.sph.util.dto.OrderRequestDto;
import com.sph.util.dto.ResponseDto;

@FeignClient(name = "PaymentService", value = "PaymentService")
public interface PaymentClient {
	
	@PostMapping("/payment/process")
	ResponseDto<?> processPayment(OrderRequestDto request);

	

}
