package com.sph.book.service;

import com.sph.book.dto.CheckoutRequestDto;
import com.sph.book.dto.OrderRequestDto;
import com.sph.book.entity.Bookings;
import com.sph.util.dto.ResponseDto;

public interface BookingsService {

	ResponseDto<?> checkout(CheckoutRequestDto request);

	ResponseDto<?> orderProduct(OrderRequestDto request);
}
