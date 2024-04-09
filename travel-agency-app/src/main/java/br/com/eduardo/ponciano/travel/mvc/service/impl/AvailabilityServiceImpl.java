package br.com.eduardo.ponciano.travel.mvc.service.impl;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import br.com.eduardo.ponciano.travel.commons.model.dto.RoomSelectionDTO;
import br.com.eduardo.ponciano.travel.commons.model.dto.RoomType;
import br.com.eduardo.ponciano.travel.mvc.model.Availability;
import br.com.eduardo.ponciano.travel.mvc.model.Hotel;
import br.com.eduardo.ponciano.travel.mvc.model.Room;
//import br.com.eduardo.ponciano.travel.mvc.model.dto.RoomSelectionDTO;
import br.com.eduardo.ponciano.travel.mvc.repository.AvailabilityRepository;
import br.com.eduardo.ponciano.travel.mvc.service.AvailabilityService;
import br.com.eduardo.ponciano.travel.mvc.service.HotelService;
import br.com.eduardo.ponciano.travel.mvc.service.RoomService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class AvailabilityServiceImpl implements AvailabilityService {

    private final AvailabilityRepository availabilityRepository;
    private final HotelService hotelService;
    private final RoomService roomService;

    @Override
    public Integer getMinAvailableRooms(Long roomId, LocalDate checkinDate, LocalDate checkoutDate) {
        log.info("Fetching minimum available rooms for room ID {} from {} to {}", roomId, checkinDate, checkoutDate);

        Room room = roomService.findRoomById(roomId).orElseThrow(() -> new EntityNotFoundException("Room not found"));

        // Fetch the minimum available rooms throughout the booking range for a room ID.
        return availabilityRepository.getMinAvailableRooms(roomId, checkinDate, checkoutDate)
                .orElse(room.getRoomCount()); // Consider no record as full availability
    }

    @Override
    public void updateAvailabilities(long hotelId, LocalDate checkinDate, LocalDate checkoutDate, List<RoomSelectionDTO> roomSelections) {
        Hotel hotel = hotelService.findHotelById(hotelId).orElseThrow(() -> new EntityNotFoundException("Hotel not found"));
        log.info("Attempting to update availabilities for hotel ID {} from {} to {}", hotelId, checkinDate, checkoutDate);

        roomSelections = roomSelections.stream()
                .filter(roomSelection -> roomSelection.getCount() > 0)
                .collect(Collectors.toList());

        // Iterate through the room selections made by the user
        for (RoomSelectionDTO roomSelection : roomSelections) {
            RoomType roomType = roomSelection.getRoomType();
            int selectedCount = roomSelection.getCount();
            log.debug("Processing {} room(s) of type {}", selectedCount, roomType);

            // Find the room by roomType for the given hotel
            Room room = hotel.getRooms().stream()
                    .filter(r -> r.getRoomType() == roomType)
                    .findFirst()
                    .orElseThrow(() -> new EntityNotFoundException("Room type not found"));

            // Iterate through the dates and update or create availability
            for (LocalDate date = checkinDate; date.isBefore(checkoutDate); date = date.plusDays(1)) {
                final LocalDate currentDate = date; // Temporary final variable
                Availability availability = availabilityRepository.findByRoomIdAndDate(room.getId(), date)
                        .orElseGet(() -> Availability.builder()
                                .hotel(hotel)
                                .date(currentDate)
                                .room(room)
                                .availableRooms(room.getRoomCount())
                                .build());

                // Reduce the available rooms by the selected count
                int updatedAvailableRooms = availability.getAvailableRooms() - selectedCount;
                if (updatedAvailableRooms < 0) {
                    throw new IllegalArgumentException("Selected rooms exceed available rooms for date: " + currentDate);
                }
                availability.setAvailableRooms(updatedAvailableRooms);

                availabilityRepository.save(availability);
            }
        }
        log.info("Successfully updated availabilities for hotel ID {} from {} to {}", hotelId, checkinDate, checkoutDate);
    }

}