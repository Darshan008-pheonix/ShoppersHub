package com.sph.book.entity;

import java.time.Instant;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.sph.util.model.PaymentStatus;
import com.sph.util.model.PaymentType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/*
 * bookid
 * uid
 * email
 * phno
 * pid
 * receiptid
 * status ->order,processing payment,payment completed,shipping,shipped,in-delivery
 * order-date 
 * delivered date
 * defined eta
 * actual eta
 * delivery-partnerid
 * sellerid/ownerid
 * 
 */

@NoArgsConstructor
@AllArgsConstructor
@Data
@Document
public class Bookings{
	
	@Id
    private String bookingId;

	
    private String userId;
    private String productId;
    private String ownerId;

    private int qty;
    private double totalcost;
    private BookingStatus bookingStatus;
    private PaymentStatus paymentStatus;
    private PaymentType paymentType;  

    private Instant createdAt;
    private Instant updatedAt;

}
