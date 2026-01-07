package com.byaniket.jetsetstay.service;

import com.byaniket.jetsetstay.dto.BookingDTO;
import com.byaniket.jetsetstay.dto.BookingRequestDTO;
import com.byaniket.jetsetstay.dto.GuestDTO;
import com.byaniket.jetsetstay.entity.*;
import com.byaniket.jetsetstay.enums.BookingStatus;
import com.byaniket.jetsetstay.exception.ResourceNotFound;
import com.byaniket.jetsetstay.repository.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class BookingServiceImpl implements BookingService {
    private final GuestRepository guestRepository;
    private final ModelMapper modelMapper;
    private final InventoryRepository inventoryRepository;
    private final RoomRepository roomRepository;

    private final BookingRepository bookingRepository;
    private final HotelRepository hotelRepository;

    @Override
    @Transactional
    public BookingDTO initializeBooking(BookingRequestDTO bookingRequestDTO) {

        log.info("Initializing booking for hotel with id: {}, having rooms with id: {}, between {} and {}", bookingRequestDTO.hotelId(),
                    bookingRequestDTO.roomId(),
                    bookingRequestDTO.checkinDate(),
                    bookingRequestDTO.checkoutDate());

        Hotel hotel = hotelRepository.findById(bookingRequestDTO.hotelId()).orElseThrow(() ->
                new ResourceNotFound("Hotel not found with id: {}" + bookingRequestDTO.hotelId()));

        Room room = roomRepository.findById(bookingRequestDTO.roomId()).orElseThrow(() ->
                new ResourceNotFound("Room not found with id: {}" + bookingRequestDTO.roomId()));

        List<Inventory> inventoryList = inventoryRepository.findAndLockAvailableInventory(
                bookingRequestDTO.roomId(),
                bookingRequestDTO.checkinDate(),
                bookingRequestDTO.checkoutDate(),
                bookingRequestDTO.roomsCount()
        );

        Long daysCount = ChronoUnit.DAYS.between(bookingRequestDTO.checkinDate(), bookingRequestDTO.checkoutDate()) + 1;

        if(inventoryList.size() != daysCount) {
            throw new IllegalStateException("Room is not available on one of the days");
        }

        for(Inventory inventory: inventoryList) {
            inventory.setReservedCount(bookingRequestDTO.roomsCount());
        }

        inventoryRepository.saveAll(inventoryList);

        User user = new User();
        user.setId(1L);

        Booking booking = Booking.builder()
                .bookingStatus(BookingStatus.RESERVED)
                .hotel(hotel)
                .room(room)
                .checkInDate(bookingRequestDTO.checkinDate())
                .checkOutDate(bookingRequestDTO.checkoutDate())
                .user(user)
                .roomsCount(bookingRequestDTO.roomsCount())
                .amount(BigDecimal.TEN)
                .build();

        booking = bookingRepository.save(booking);
        return modelMapper.map(booking, BookingDTO.class);
    }

    @Override
    public BookingDTO addGuests(Long bookingId, List<GuestDTO> guests) {
        log.info("service: adding guests for booking with id: ", bookingId);

        Booking booking = bookingRepository.findById(bookingId).orElseThrow(() ->
                new ResourceNotFound("Booking not found with id: {}" + bookingId));

        //if the booking was created 10mins before current time then it has expired
        if(booking.getCreatedAt().plusMinutes(10).isBefore(LocalDateTime.now())) {
            throw new IllegalStateException("Booking has already expired");
        }

        if(booking.getBookingStatus() != BookingStatus.RESERVED) {
            throw new IllegalStateException("Booking is not under reserved state, cannot add guests");
        }

        User user = new User();
        user.setId(1L);

        for(GuestDTO guestDto: guests) {
            Guest guest = modelMapper.map(guestDto, Guest.class);

            guest.setUser(user);
            guestRepository.save(guest);
            booking.getGuests().add(guest);
        }

        booking.setBookingStatus(BookingStatus.GUESTS_ADDED);
        booking = bookingRepository.save(booking);

        return modelMapper.map(booking, BookingDTO.class);
    }
}
