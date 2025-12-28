package com.byaniket.jetsetstay.service;

import com.byaniket.jetsetstay.dto.RoomDTO;
import com.byaniket.jetsetstay.entity.Hotel;
import com.byaniket.jetsetstay.entity.Room;
import com.byaniket.jetsetstay.exception.ResourceNotFound;
import com.byaniket.jetsetstay.repository.HotelRepository;
import com.byaniket.jetsetstay.repository.RoomRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class RoomServiceImpl implements RoomService{

    private final RoomRepository roomRepository;
    private final HotelRepository hotelRepository;
    private final InventoryService inventoryService;
    private final ModelMapper modelMapper;

    @Override
    public RoomDTO createNewRoom(RoomDTO roomDTO, Long hotelId) {
        log.info("service: creating new room in hotel with id: {} ", hotelId);
        //check if hotel exists
        Hotel hotel = hotelRepository.findById(hotelId).orElseThrow(() -> new ResourceNotFound("Hotel with ID: {} not found" + hotelId));
        Room room = modelMapper.map(roomDTO, Room.class);
        room.setHotel(hotel);
        room = roomRepository.save(room);

        if(hotel.getActive()) { //if active initialize rooms for a year in inventory
            inventoryService.initializeRoomForAYear(room);
        }
        return modelMapper.map(room, RoomDTO.class);
    }

    @Override
    public List<RoomDTO> getAllRoomsinHotel(Long hotelId) {
        log.info("service: finding all rooms in hotel with id: {} ", hotelId);
        //check if hotel exists
        Hotel hotel = hotelRepository.findById(hotelId).orElseThrow(() -> new ResourceNotFound("Hotel with ID: {} not found" + hotelId));
        List<Room> rooms= hotel.getRooms();
        return rooms
                .stream()
                .map((element) -> modelMapper.map(element, RoomDTO.class))
                .collect(Collectors.toList());
    }

    // probably got no use to find a room by its id?
//    @Override
//    public RoomDTO getRoomById(Long roomId) {
//        Room room = roomRepository.findById(roomId).orElseThrow(() -> new ResourceNotFound("Room with ID: {} not found" + roomId));
//        return modelMapper.map(room, RoomDTO.class);
//    }

    @Override
    public void deleteRoomById(Long roomId) {
        log.info("service: deleting room with id: {} ", roomId);
        Room room = roomRepository.findById(roomId).orElseThrow(() -> new ResourceNotFound("Room with ID: {} not found" + roomId));
        roomRepository.deleteById(roomId);

    }
}
