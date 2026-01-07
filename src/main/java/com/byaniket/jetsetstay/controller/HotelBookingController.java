package com.byaniket.jetsetstay.controller;

import com.byaniket.jetsetstay.dto.BookingDTO;
import com.byaniket.jetsetstay.dto.BookingRequestDTO;
import com.byaniket.jetsetstay.dto.GuestDTO;
import com.byaniket.jetsetstay.service.BookingService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/booking")
public class HotelBookingController {

    private final BookingService bookingService;

    @PostMapping("/init")
    public ResponseEntity<BookingDTO> initializeBooking(@RequestBody BookingRequestDTO bookingRequestDTO) {
        return ResponseEntity.ok(bookingService.initializeBooking(bookingRequestDTO));
    }

    @PostMapping("/{bookingId}/addGuests")
    public ResponseEntity<BookingDTO> addGuests(@PathVariable Long bookingId,
                                                @RequestBody List<GuestDTO> guests) {
        return ResponseEntity.ok(bookingService.addGuests(bookingId, guests));
    }

}
