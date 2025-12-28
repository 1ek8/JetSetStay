package com.byaniket.jetsetstay.service;

import com.byaniket.jetsetstay.dto.RoomDTO;

import java.util.List;

public interface RoomService {

    RoomDTO createNewRoom(RoomDTO roomDTO, Long hotelId);

    List<RoomDTO> getAllRoomsinHotel(Long hotelId);

//    RoomDTO getRoomById(Long roomId);

    void deleteRoomById(Long roomId);

}
