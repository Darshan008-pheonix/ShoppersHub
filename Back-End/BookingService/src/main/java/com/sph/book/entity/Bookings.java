package com.sph.book.entity;

import java.time.Instant;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

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


@Document
public class Bookings{
	
	@Id
    private String bookingId;

	
    private String userId;
    private String productId;
    private String sellerId;

    private BookingStatus bookingStatus;
    private PaymentStatus paymentStatus;

    private String receiptId;   

    private Instant createdAt;
    private Instant updatedAt;

}
