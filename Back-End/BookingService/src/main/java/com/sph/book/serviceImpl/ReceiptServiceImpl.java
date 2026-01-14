package com.sph.book.serviceImpl;

import java.time.Instant;

import org.springframework.stereotype.Service;

import com.sph.book.dao.BookingsDao;
import com.sph.book.entity.BookingStatus;
import com.sph.book.entity.Bookings;
import com.sph.book.service.ReceiptService;
import com.sph.util.dto.PaymentAndRecpietDto;
import com.sph.util.model.PaymentStatus;

import lombok.RequiredArgsConstructor;

@Service   // ⭐ THIS fixes your startup crash
@RequiredArgsConstructor
public class ReceiptServiceImpl implements ReceiptService {

    private final BookingsDao bookingsDao;

    @Override
    public String sendReceipt(PaymentAndRecpietDto event) {

        Bookings booking = bookingsDao.findByTransactionId(event.getTranxId());

        if (booking == null) {
            return "Booking not found";
        }

        if (event.getPaymentStatus() == PaymentStatus.SUCCESS) {
            booking.setPaymentStatus(PaymentStatus.SUCCESS);
            booking.setBookingStatus(BookingStatus.ORDER_PLACED);
        } else {
            booking.setPaymentStatus(PaymentStatus.FAILED);
            booking.setBookingStatus(BookingStatus.CANCELLED);
        }

        booking.setUpdatedAt(Instant.now());
        bookingsDao.updateBooking(booking);

        return "Receipt processed for Booking " + booking.getBookingId();
    }
}
