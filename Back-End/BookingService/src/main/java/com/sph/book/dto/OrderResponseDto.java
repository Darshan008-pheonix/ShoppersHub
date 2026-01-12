package com.sph.book.dto;

import com.sph.book.entity.PaymentType;

import lombok.Data;

@Data
public class OrderResponseDto {

	String BookingId;
	PaymentType paymentType;
	
}
