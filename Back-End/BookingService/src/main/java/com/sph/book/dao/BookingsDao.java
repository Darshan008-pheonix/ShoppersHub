package com.sph.book.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.FindAndModifyOptions;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
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
		if (bookingSaga == null) {
			throw new IllegalArgumentException("bookingSaga must not be null");
		}
       
		MongoTemplate dbCon = mongoConfig.getConnection("BookingSaga");

		if (ObjectUtils.isEmpty(dbCon.findById(bookingSaga.getSagaId(), BookingSaga.class))) {
			return dbCon.save(bookingSaga);
		}

		Query query = new Query(Criteria.where("sagaId").is(bookingSaga.getSagaId()));
		Update update = new Update();
		if (bookingSaga.getBookingId() != null) {
			update.set("bookingId", bookingSaga.getBookingId());
		}
		if (bookingSaga.getCurrentStep() != null) {
			update.set("currentStep", bookingSaga.getCurrentStep());
		}
		if (bookingSaga.getUpdatedAt() != null) {
			update.set("updatedAt", bookingSaga.getUpdatedAt());
		}
		if (bookingSaga.getFailureReason() != null) {
			update.set("failureReason", bookingSaga.getFailureReason());
		}
		FindAndModifyOptions options = new FindAndModifyOptions().upsert(true).returnNew(true);
		BookingSaga result = dbCon.findAndModify(query, update, options, BookingSaga.class);

		if (result == null) {
			return dbCon.save(bookingSaga);
		}
		return result;
	}


}