package com.byaniket.jetsetstay.dto;

import java.time.LocalDate;
import java.util.Objects;

public record HotelSearchRequest(
        String city,

        LocalDate startDate,

        LocalDate endDate,

        Integer roomsCount,

        Integer page,

        Integer size,

        Integer dateCount
) {

    public HotelSearchRequest {
        // If page is null, set it to 0
        page = Objects.requireNonNullElse(page, 0);

        // If size is null, set it to 10
        size = Objects.requireNonNullElse(size, 10);

        // Optional: Add validation logic here
        if (page < 0) {
            throw new IllegalArgumentException("Page cannot be negative");
        }
    }
}
