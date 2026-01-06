package com.byaniket.jetsetstay.dto;

import com.byaniket.jetsetstay.entity.Guest;
import com.byaniket.jetsetstay.entity.Hotel;
import com.byaniket.jetsetstay.entity.Room;
import com.byaniket.jetsetstay.entity.User;
import com.byaniket.jetsetstay.enums.BookingStatus;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;

public record BookingDTO(

        Long Id,

        Hotel hotel,

        RoomDTO room,

        User user,

        Integer roomsCount,

        LocalDate checkInDate,

        LocalDate checkOutDate,

        LocalDateTime createdAt,

        BookingStatus bookingStatus,

        Set<GuestDTO> guests

) {
}
