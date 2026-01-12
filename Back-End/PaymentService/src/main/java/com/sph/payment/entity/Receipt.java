package com.sph.payment.entity;

import java.time.Instant;

import org.springframework.data.mongodb.core.mapping.Document;

import com.sph.util.model.Address;
import com.sph.util.model.PaymentStatus;
import com.sph.util.model.PaymentType;

import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Document
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Receipt {
	
	@Id
    private String receiptId;

    private String bookingId;
    private String userId;

    private PaymentType paymentType;
    private PaymentStatus paymentStatus;
    private String paymentTransactionId;

    private double itemPrice;
    private double taxAmount;
    private double deliveryCharge;
    private double totalAmount;

    private Address billingAddress;
    private Address deliveryAddress;

    private Instant generatedAt;

}
