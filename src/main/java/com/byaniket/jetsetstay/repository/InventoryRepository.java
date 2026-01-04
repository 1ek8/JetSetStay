package com.byaniket.jetsetstay.repository;

import com.byaniket.jetsetstay.entity.Hotel;
import com.byaniket.jetsetstay.entity.Inventory;
import com.byaniket.jetsetstay.entity.Room;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;

public interface InventoryRepository extends JpaRepository<Inventory, Long> {

    void deleteByRoom(Room room);
}
