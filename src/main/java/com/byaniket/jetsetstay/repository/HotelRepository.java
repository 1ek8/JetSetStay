package com.byaniket.jetsetstay.repository;

import com.byaniket.jetsetstay.entity.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HotelRepository extends JpaRepository<Hotel, Long> {



}
