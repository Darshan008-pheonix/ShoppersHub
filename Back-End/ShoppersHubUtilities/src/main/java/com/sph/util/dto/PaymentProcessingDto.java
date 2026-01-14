package com.sph.util.dto;

import com.sph.util.model.BookingStatus;
import com.sph.util.model.PaymentStatus;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PaymentProcessingDto {
	
	private String tranxId;
	private PaymentStatus paymentStatus;   // SUCCESS / FAILED
    private BookingStatus bookingStatus;   // CONFIRMED / CANCELLED

}
