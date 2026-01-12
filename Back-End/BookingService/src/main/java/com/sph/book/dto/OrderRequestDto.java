package com.sph.book.dto;

import com.sph.book.entity.PaymentType;
import com.sph.util.model.Address;

public record OrderRequestDto(String productId,
		String userId,
		String BookingId,
		String ownerID,
		int quantity,
		double totalCost,
		double tax,
		double deliveryfee,
		PaymentType paymentType,
		String receiver_number,
		Address billingAddress,
		Address deliveryAddress) {

}
