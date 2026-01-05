package com.byaniket.jetsetstay.dto;

import java.util.List;

public record HotelInfoDTO(

        HotelDTO hotelDTO,

        List<RoomDTO> rooms

) {
}
