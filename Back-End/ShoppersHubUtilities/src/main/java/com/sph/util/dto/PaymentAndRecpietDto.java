package com.sph.util.dto;

import com.sph.util.model.Address;
import com.sph.util.model.PaymentStatus;
import com.sph.util.model.PaymentType;

import lombok.Data;

@Data
public class PaymentAndRecpietDto {
	
	
	String productId;
	String userId;
	String BookingId;
	double itemPrice;
	String ownerID;
	int quantity;
	double totalCost;
	double tax;
	double deliveryfee;
	PaymentType paymentType;
	String receiver_number;
	Address billingAddress;
	Address deliveryAddress;
	String tranxId;
	PaymentStatus paymentStatus;
	

}
