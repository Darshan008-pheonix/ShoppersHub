package com.sph.book.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Component;

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
		
		
		return  dbCon.save(bookingSaga);
	}


}
