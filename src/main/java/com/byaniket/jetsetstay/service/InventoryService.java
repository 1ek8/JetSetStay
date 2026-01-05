package com.byaniket.jetsetstay.service;

import com.byaniket.jetsetstay.dto.HotelDTO;
import com.byaniket.jetsetstay.dto.HotelSearchRequest;
import com.byaniket.jetsetstay.entity.Room;
import org.springframework.data.domain.Page;

public interface InventoryService {

    void initializeRoomForAYear(Room room);

    void deleteAllInventories(Room room);

    Page<HotelDTO> searchHotels(HotelSearchRequest hotelSearchRequest);
}
