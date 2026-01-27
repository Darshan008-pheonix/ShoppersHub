package com.sph.payment.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.sph.payment.service.PaymentService;
import com.sph.util.dto.OrderRequestDto;
import com.sph.util.dto.ResponseDto;

@RestController
public class PaymentController {

	@Autowired
	PaymentService paymentService;
	
	@PostMapping("/payment/process")
	ResponseDto<?> processPayment(@RequestBody OrderRequestDto request) {
		return paymentService.processPayment(request);
	
	}
	
	@GetMapping("/msg")
	String getMsg() {
		return "Payment Service is up and running!";
	}
	
	
}
