package com.sph.payment.service;

import com.sph.util.dto.OrderRequestDto;
import com.sph.util.dto.ResponseDto;

public interface PaymentService {

	ResponseDto<?> processPayment(OrderRequestDto request);

}
