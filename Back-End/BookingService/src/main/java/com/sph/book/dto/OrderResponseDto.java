package com.sph.book.dto;

import com.sph.util.model.PaymentType;

import lombok.Data;

@Data
public class OrderResponseDto {

	String BookingId;
	PaymentType paymentType;
	
}
