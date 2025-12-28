package com.byaniket.jetsetstay.service;

import com.byaniket.jetsetstay.dto.HotelDTO;
import com.byaniket.jetsetstay.entity.Hotel;
import com.byaniket.jetsetstay.entity.Room;
import com.byaniket.jetsetstay.exception.ResourceNotFound;
import com.byaniket.jetsetstay.repository.HotelRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class HotelServiceImpl implements HotelService {

    private final HotelRepository hotelRepository;
    private final ModelMapper modelMapper;
    private final InventoryService inventoryService;

    @Override
    public HotelDTO createNewHotel(HotelDTO hotelDto) {
        log.info("service: hotel creation with name: {}", hotelDto.name());
        Hotel hotel = modelMapper.map(hotelDto, Hotel.class);
        hotel.setActive(false); //initially hotel's inventory is inactive, becomes active upon expansion
        hotel = hotelRepository.save(hotel);
        log.info("hotel creation with id: {}", hotelDto.Id());
        return modelMapper.map(hotel, HotelDTO.class);
    }

    @Override
    public HotelDTO getHotelById(Long Id) {
        log.info("service: hotel fetch with id: {}", Id);
        Hotel hotel = hotelRepository.findById(Id).orElseThrow(() -> new ResourceNotFound("Hotel not found with id: " + Id));
        return modelMapper.map(hotel, HotelDTO.class);
    }

    @Override
    public HotelDTO updateHotelById(Long hotelId, HotelDTO hotelDTO) {
        log.info("service: hotel fetch with id: {}", hotelId);
        Hotel hotel = hotelRepository
                .findById(hotelId)
                .orElseThrow(() -> new ResourceNotFound("Hotel not found with id: " + hotelId));
        modelMapper.map(hotelDTO, hotel);
        hotel.setId(hotelId); // hotelDTO object may not have id, in thta case mapped hotel entity class' ID becomes null. hotelRepo.save() requires its input entity to have id
        hotel = hotelRepository.save(hotel);
        return modelMapper.map(hotel, HotelDTO.class);
    }

    @Override
    public Boolean deleteHotelById(Long hotelId) {
        log.info("service: hotel delete with id: {}", hotelId);
        boolean exists = hotelRepository.existsById(hotelId);
        if(!exists) throw new ResourceNotFound("Hotel not found with id: " + hotelId);
        hotelRepository.deleteById(hotelId);

        //after deleting hotel all the remaining hotel entries in future should be deleted too
        Hotel hotel = hotelRepository
                .findById(hotelId)
                .orElseThrow(() -> new ResourceNotFound("Hotel not found with id: " + hotelId));
        for(Room room: hotel.getRooms()) {
            inventoryService.deleteFutureInventories(room);
        }
        return true;
    }

    @Override
    public void activateHotel(Long hotelId) {
        log.info("service: hotel activate with id: {}", hotelId);
        Hotel hotel = hotelRepository
                .findById(hotelId)
                .orElseThrow(() -> new ResourceNotFound("Hotel not found with id: " + hotelId));
        hotel.setActive(true);

        //needs to be done only once
        for(Room room: hotel.getRooms()) {
            inventoryService.initializeRoomForAYear(room);
        }
    }


//    @Override
//    public List<HotelDTO> getAllHotels() {
//        List<Hotel> hotelList = hotelRepository.findAll();
//        return hotelList
//                .stream()
//                .map(hotel -> modelMapper.map(hotel, HotelDTO.class))
//                .collect(Collectors.toList());
//    }
}
