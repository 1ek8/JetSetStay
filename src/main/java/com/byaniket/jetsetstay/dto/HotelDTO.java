package com.byaniket.jetsetstay.dto;

import com.byaniket.jetsetstay.entity.HotelContactInfo;
import com.byaniket.jetsetstay.entity.User;

import java.time.LocalDateTime;

public record HotelDTO(

        Long Id,

        String name,

        String city,

        String[] photos,

        String[] amenities,

        HotelContactInfo contactInfo,

        Boolean active,

        User owner
) {
}
