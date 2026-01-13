package com.sph.book.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import com.sph.book.config.MongoConfig;
import com.sph.book.entity.BookingSaga;
import com.sph.book.entity.Bookings;

@Component
public class BookingsDao {
	

	@Autowired
	MongoConfig mongoConfig;
	
	public Bookings createBooking(Bookings bookings) {
		MongoTemplate dbCon = mongoConfig.getConnection("Bookings");
		
		Bookings bookings2 =dbCon.save(bookings);
		return bookings2;
	}

	public BookingSaga updateSaga(BookingSaga bookingSaga) {
		MongoTemplate dbCon = mongoConfig.getConnection("BookingSaga");
		BookingSaga sag=dbCon.findById(bookingSaga.getBookingId(),BookingSaga.class);
		
		if(ObjectUtils.isEmpty(sag)) {
		return  dbCon.save(bookingSaga);
		}
		else {
			sag.setBookingId(bookingSaga.getBookingId());
			sag.setCurrentStep(bookingSaga.getCurrentStep());
			sag.setUpdatedAt(bookingSaga.getUpdatedAt());
			sag.setFailureReason(bookingSaga.getFailureReason());
			return dbCon.save(sag);
		}
	}


}
