package com.byaniket.jetsetstay.service;

import com.byaniket.jetsetstay.dto.HotelDTO;
import com.byaniket.jetsetstay.entity.Hotel;
import com.byaniket.jetsetstay.exception.ResourceNotFound;
import com.byaniket.jetsetstay.repository.HotelRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class HotelServiceImpl implements HotelService {

    private final HotelRepository hotelRepository;
    private final ModelMapper modelMapper;

    @Override
    public HotelDTO createNewHotel(HotelDTO hotelDto) {
        log.info("hotel creation with name: {}", hotelDto.name());
        Hotel hotel = modelMapper.map(hotelDto, Hotel.class);
        hotel.setActive(false); //initially hotel's inventory is inactive, becomes active upon expansion
        hotel = hotelRepository.save(hotel);
        log.info("hotel creation with id: {}", hotelDto.Id());
        return modelMapper.map(hotel, HotelDTO.class);
    }

    @Override
    public HotelDTO getHotelById(Long Id) {
        Hotel hotel = hotelRepository.findById(Id).orElseThrow(() -> new ResourceNotFound("Hotel not found with id: " + Id));
        return modelMapper.map(hotel, HotelDTO.class);
    }
}
