package com.byaniket.jetsetstay.service;

import com.byaniket.jetsetstay.dto.HotelDTO;
import com.byaniket.jetsetstay.entity.Hotel;

public interface HotelService {

    HotelDTO createNewHotel(HotelDTO hotelDto);

    HotelDTO getHotelById(Long Id);
}
