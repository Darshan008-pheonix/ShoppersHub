package com.sph.book.service;

import com.sph.book.entity.Bookings;

public interface BookingsService {
		Bookings checkout(String pId,String uID, int quat);
		String orderNow(Bookings booking);
		Bookings payNow(String bookingId, String paymentMode);
		
		
}
