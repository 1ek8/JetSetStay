package com.byaniket.jetsetstay.controller;

import com.byaniket.jetsetstay.dto.HotelDTO;
import com.byaniket.jetsetstay.service.HotelService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping("/{hotelId}")
    public ResponseEntity<HotelDTO> getHotelById(@PathVariable Long hotelId) {
        log.info("attemptiing to fetch info about hotel with id: {}", hotelId);
        HotelDTO hotel = hotelService.getHotelById(hotelId);
        return ResponseEntity.ok(hotel);
    }

    @PutMapping("/hotelId")
    public ResponseEntity<HotelDTO> updateHotelById(@PathVariable Long hotelId, @RequestBody HotelDTO hotelDTO) {
        log.info("attemptiing to update info about hotel with id: {}", hotelId);
        HotelDTO hotel = hotelService.updateHotelById(hotelId, hotelDTO);
        return ResponseEntity.ok(hotel);
    }

    @DeleteMapping("/hotelId")
    public ResponseEntity<Boolean> deleteHotelById(@PathVariable Long hotelId) {
        log.info("attemptiing to delete info about hotel with id: {}", hotelId);
        Boolean success = hotelService.deleteHotelById(hotelId);
        return ResponseEntity.ok(success);
    }

    @PatchMapping("/hotelId")
    public ResponseEntity<Void> activateHotel(@PathVariable Long hotelId) {
        log.info("attemptiing to activate hotel with id: {}", hotelId);
        hotelService.activateHotel(hotelId);
        return ResponseEntity.noContent().build();
    }


}
