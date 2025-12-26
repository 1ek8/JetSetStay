package com.byaniket.jetsetstay.dto;

import com.byaniket.jetsetstay.entity.Hotel;

import java.math.BigDecimal;

public record RoomDTO(

        Long Id,

        Hotel hotel,

        String type,

        BigDecimal basePrice,

        String[] photos,

        String[] amenities,

        Integer capacity,

        Integer totalCount

) {
}
