package com.data.session04.repository;

import com.data.session04.entity.Booking;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookingRepository extends JpaRepository<Booking, Integer> {
    List<Booking> findByCustomerPhone(String phone);
}
