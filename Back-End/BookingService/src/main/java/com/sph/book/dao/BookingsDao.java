package com.sph.book.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Component;

import com.sph.book.config.MongoConfig;

@Component
public class BookingsDao {
	

	@Autowired
	MongoConfig mongoConfig;
	
	public void createBooking() {
		MongoTemplate dbCon = mongoConfig.getConnection("Bookings");
	}


}
