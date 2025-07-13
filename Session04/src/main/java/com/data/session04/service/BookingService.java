package com.data.session04.service;

import com.data.session04.entity.Booking;
import com.data.session04.entity.BookingStatus;
import com.data.session04.entity.Flight;
import com.data.session04.repository.BookingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BookingService {

    private final BookingRepository bookingRepository;

    public Booking bookFlight(Flight flight, String name, String phone) {
        Booking booking = new Booking();
        booking.setFlight(flight);
        booking.setCustomerName(name);
        booking.setCustomerPhone(phone);
        booking.setBookingTime(LocalDateTime.now());
        booking.setStatus(BookingStatus.BOOKED);
        return bookingRepository.save(booking);
    }

    public List<Booking> getBookingsByPhone(String phone) {
        return bookingRepository.findByCustomerPhone(phone);
    }

    public void cancelBooking(Integer id) {
        bookingRepository.findById(id).ifPresent(booking -> {
            booking.setStatus(BookingStatus.CANCELLED);
            bookingRepository.save(booking);
        });
    }
}
