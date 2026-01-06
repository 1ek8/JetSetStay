package com.byaniket.jetsetstay.service;

import com.byaniket.jetsetstay.dto.HotelDTO;
import com.byaniket.jetsetstay.dto.HotelSearchRequest;
import com.byaniket.jetsetstay.entity.Hotel;
import com.byaniket.jetsetstay.entity.Inventory;
import com.byaniket.jetsetstay.entity.Room;
import com.byaniket.jetsetstay.repository.InventoryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

@Service
@Slf4j
@RequiredArgsConstructor
public class InventoryServiceImpl implements InventoryService{

    private final InventoryRepository inventoryRepository;
    private final ModelMapper modelMapper;

    @Override
    public void initializeRoomForAYear( Room room) {
        log.info("service: Initialize inventory for 1 year for room with id: {} ", room.getId());
        LocalDate today =  LocalDate.now();
        LocalDate endDate = today.plusYears(1);
        for (; !today.isAfter(endDate); today = today.plusDays(1)) {
            Inventory inventory = Inventory.builder()
                    .hotel(room.getHotel())
                    .room(room)
                    .city(room.getHotel().getCity())
                    .date(today)
                    .surgeFactor(BigDecimal.ONE)
                    .totalCount(room.getTotalCount())
                    .build();
            inventoryRepository.save(inventory);
        }
    }

    @Override
    public void deleteAllInventories(Room room) {
        log.info("service: delete all inventory for room with id: {}", room.getId());
        LocalDate today = LocalDate.now();
        inventoryRepository.deleteByRoom( room);
    }

    @Override
    public Page<HotelDTO> searchHotels(HotelSearchRequest hotelSearchRequest) {
        log.info("service: search for all hotels in city {} between dates {} and {} having {} rooms vacant",
                hotelSearchRequest.city(),
                hotelSearchRequest.startDate(),
                hotelSearchRequest.endDate(),
                hotelSearchRequest.roomsCount());

        Long dateCount = ChronoUnit.DAYS.between(hotelSearchRequest.startDate(), hotelSearchRequest.endDate()) + 1;
        Pageable pageable = PageRequest.of(hotelSearchRequest.page(), hotelSearchRequest.size());

        Page<Hotel> hotelPage = inventoryRepository.findHotelsWithAvailableInventory(hotelSearchRequest.city(),
                hotelSearchRequest.startDate(),
                hotelSearchRequest.endDate(),
                hotelSearchRequest.roomsCount(),
                dateCount,
                pageable);
        return hotelPage.map((element) -> modelMapper.map(element, HotelDTO.class));
    }

}
