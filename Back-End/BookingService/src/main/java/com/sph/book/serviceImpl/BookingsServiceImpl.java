package com.sph.book.serviceImpl;

import java.time.Instant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sph.book.entity.BookingStatus;
import com.sph.book.entity.Bookings;
import com.sph.book.entity.PaymentStatus;
import com.sph.book.repo.BookingsRepo;
import com.sph.book.service.BookingsService;

@Service
public class BookingsServiceImpl implements BookingsService{

	private final BookingsRepo bookingsRepo;

    public BookingsServiceImpl(BookingsRepo bookingsRepo) {
        this.bookingsRepo = bookingsRepo;
    }

	
	@Override
	public Bookings checkout(String pId, String uID, int quat) {
	    Bookings booking = new Bookings();
        booking.setProductId(pId);
        booking.setUserId(uID);
        booking.setBookingStatus(BookingStatus.ORDER_PLACED);
        booking.setPaymentStatus(PaymentStatus.PENDING);
        booking.setCreatedAt(Instant.now());

        return bookingsRepo.save(booking);	
        }

	@Override
	public String orderNow(Bookings booking) {
		return null;
	}

	@Override
	public Bookings payNow(String bookingId, String paymentMode) {
		return null;
	}

}
