package com.byaniket.jetsetstay.dto;

import java.time.LocalDate;

public record BookingRequestDTO(

        Long hotelId,

        Long roomId,

        LocalDate checkinDate,

        LocalDate checkoutDate,

        Integer roomsCount

) {
}
