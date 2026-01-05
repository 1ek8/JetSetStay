package com.byaniket.jetsetstay.controller;

import com.byaniket.jetsetstay.dto.HotelDTO;
import com.byaniket.jetsetstay.dto.HotelSearchRequest;
import com.byaniket.jetsetstay.service.InventoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/hotels")
@RequiredArgsConstructor
public class HotelBrowseController {

    private final InventoryService inventoryService;

    @GetMapping("/search")
    public ResponseEntity<Page<HotelDTO>> searchHotels(@RequestBody HotelSearchRequest hotelSearchRequest) {
        Page<HotelDTO> page = inventoryService.searchHotels(hotelSearchRequest);
        return ResponseEntity.ok(page);
    }

}
