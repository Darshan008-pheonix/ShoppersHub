package com.sph.book.dto;

import com.sph.util.model.Address;

public record OrderRequestDto(String productId,String userId,
		int quantity,double totalCost,double tax,double deliveryfee,String PaymentType,
		String receiver_number,Address billingAddress,Address deliveryAddress) {

}
