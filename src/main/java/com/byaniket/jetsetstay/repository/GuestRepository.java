package com.byaniket.jetsetstay.repository;

import com.byaniket.jetsetstay.entity.Guest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GuestRepository extends JpaRepository<Guest, Long> {
}