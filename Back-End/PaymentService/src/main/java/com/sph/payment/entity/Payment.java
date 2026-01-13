package com.sph.payment.entity;

import java.time.Instant;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.sph.util.model.PaymentStatus;
import com.sph.util.model.PaymentType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Payment {

    @Id
    private String paymentId;

    private String bookingId;
    private String transactionId;
    private PaymentStatus paymentStatus;
    private PaymentType paymentType;
    private double amount;
    private String receiptId;
    private Instant createdAt;
}
