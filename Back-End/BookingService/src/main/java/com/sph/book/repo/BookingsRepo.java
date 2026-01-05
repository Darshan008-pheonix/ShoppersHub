package com.sph.book.repo;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.sph.book.entity.BookingStatus;
import com.sph.book.entity.Bookings;


public interface BookingsRepo extends MongoRepository<Bookings, String> {

    List<Bookings> findByUserId(String userId);
    List<Bookings> findBySellerId(String sellerId);
    List<Bookings> findByBookingStatus(BookingStatus status);
}
