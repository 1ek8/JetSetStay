package com.byaniket.jetsetstay.controller;

import com.byaniket.jetsetstay.dto.HotelDTO;
import com.byaniket.jetsetstay.service.HotelService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin/hotels")
@RequiredArgsConstructor
@Slf4j
public class HotelController {

    private final HotelService hotelService;

    @PostMapping
    public ResponseEntity<HotelDTO> createNewHotel(@RequestBody HotelDTO hotelDTO) {
        log.info("attempting to create new hotel with name: "+ hotelDTO.name());
        HotelDTO hotel = hotelService.createNewHotel(hotelDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(hotel);
    }

    @GetMapping


}
