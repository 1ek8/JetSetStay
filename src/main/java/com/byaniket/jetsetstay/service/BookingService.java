package com.byaniket.jetsetstay.service;

import com.byaniket.jetsetstay.dto.BookingDTO;
import com.byaniket.jetsetstay.dto.BookingRequestDTO;
import org.jspecify.annotations.Nullable;

public interface BookingService {
    BookingDTO initializeBooking(BookingRequestDTO bookingRequestDTO);
}
