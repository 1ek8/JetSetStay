package com.byaniket.jetsetstay.service;

import com.byaniket.jetsetstay.dto.HotelDTO;
import com.byaniket.jetsetstay.entity.Hotel;

import java.util.List;

public interface HotelService {

    HotelDTO createNewHotel(HotelDTO hotelDto);

    HotelDTO getHotelById(Long Id);

    HotelDTO updateHotelById(Long hotelId, HotelDTO hotelDTO);

    Boolean deleteHotelById(Long hotelId);

    void activateHotel(Long hotelId);

//    List<HotelDTO> getAllHotels();
}
