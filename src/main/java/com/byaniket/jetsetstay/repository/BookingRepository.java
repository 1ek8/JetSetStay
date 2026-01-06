package com.byaniket.jetsetstay.repository;

import com.byaniket.jetsetstay.entity.Booking;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookingRepository extends JpaRepository<Booking, Long> {



}
