package com.byaniket.jetsetstay.repository;

import com.byaniket.jetsetstay.entity.Room;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoomRepository extends JpaRepository<Room, Long> {
}
