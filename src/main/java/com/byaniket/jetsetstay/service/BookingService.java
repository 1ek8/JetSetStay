package com.byaniket.jetsetstay.service;

import com.byaniket.jetsetstay.dto.BookingDTO;
import com.byaniket.jetsetstay.dto.BookingRequestDTO;
import com.byaniket.jetsetstay.dto.GuestDTO;
import org.jspecify.annotations.Nullable;

import java.util.List;

public interface BookingService {
    BookingDTO initializeBooking(BookingRequestDTO bookingRequestDTO);

    BookingDTO addGuests(Long bookingId, List<GuestDTO> guests);
}
