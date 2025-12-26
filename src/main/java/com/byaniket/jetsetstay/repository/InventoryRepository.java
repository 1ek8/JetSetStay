package com.byaniket.jetsetstay.repository;

import com.byaniket.jetsetstay.entity.Hotel;
import com.byaniket.jetsetstay.entity.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InventoryRepository extends JpaRepository<Inventory, Long> {
}
