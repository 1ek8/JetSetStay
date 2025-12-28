package com.byaniket.jetsetstay.controller;

import com.byaniket.jetsetstay.dto.RoomDTO;
import com.byaniket.jetsetstay.service.RoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequiredArgsConstructor
@RequestMapping("/admin/hotels/{hotelId}/rooms")
public class RoomController {

    private final RoomService roomService;

    @PostMapping
    public ResponseEntity<RoomDTO> createNewRoom(@PathVariable Long roomId, @RequestBody RoomDTO roomDTO) {
        RoomDTO room = roomService.createNewRoom(roomDTO, roomId);
        return new ResponseEntity<>(room, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<RoomDTO>> getAllRoomsInHotel(@PathVariable Long hotelId) {
        return ResponseEntity.ok(roomService.getAllRoomsinHotel(hotelId));
    }

//    @GetMapping("/{roomId}")
//    public ResponseEntity<RoomDTO> getRoomsById(@PathVariable Long roomId) {
//        return ResponseEntity.ok(roomService.getRoomById(roomId));
//    }

    @DeleteMapping("/{roomId}")
    public ResponseEntity<RoomDTO> deleteRoomById(@PathVariable Long roomId) {
        roomService.deleteRoomById(roomId);
        return ResponseEntity.noContent().build();
    }
}