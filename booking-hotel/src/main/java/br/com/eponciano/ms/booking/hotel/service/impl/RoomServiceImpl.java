package br.com.eponciano.ms.booking.hotel.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import br.com.eponciano.ms.booking.commons.model.dto.RoomDTO;
import br.com.eponciano.ms.booking.hotel.mapper.RoomMapper;
import br.com.eponciano.ms.booking.hotel.model.Hotel;
import br.com.eponciano.ms.booking.hotel.model.Room;
import br.com.eponciano.ms.booking.hotel.repository.RoomRepository;
import br.com.eponciano.ms.booking.hotel.service.RoomService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class RoomServiceImpl implements RoomService {

    private final RoomRepository roomRepository;

    @Override
    public Room saveRoom(RoomDTO roomDTO) {
        log.info("Attempting to save a new room: {}", roomDTO);
        Room room = RoomMapper.dtoToEntity(roomDTO);
        room = roomRepository.save(room);
        log.info("Successfully saved room with ID: {}", room.getId());
        return room;
    }

    @Override
    public List<Room> saveRooms(List<RoomDTO> roomDTOs) {
        log.info("Attempting to save rooms: {}", roomDTOs);
        List<Room> rooms = roomDTOs.stream()
                .map(roomDTO -> saveRoom(roomDTO))
                .collect(Collectors.toList());
        log.info("Successfully saved rooms: {}", rooms);
        return rooms;
    }

    @Override
    public Optional<Room> findRoomById(Long id) {
        return roomRepository.findById(id);
    }

    @Override
    public List<Room> findRoomsByHotelId(Long hotelId) {
        return null;
    }

    @Override
    public Room updateRoom(RoomDTO roomDTO) {
        log.info("Attempting to update room with ID: {}", roomDTO.getId());
        Room existingRoom = roomRepository.findById(roomDTO.getId())
                .orElseThrow(() -> new EntityNotFoundException("Room not found"));

        log.info("Room with ID: {} found", roomDTO.getId());

//        existingRoom.setRoomType(roomDTO.getRoomType());
//        existingRoom.setRoomCount(roomDTO.getRoomCount());
//        existingRoom.setPricePerNight(roomDTO.getPricePerNight());

        Room updatedRoom = roomRepository.save(existingRoom);
        log.info("Successfully updated address with ID: {}", existingRoom.getId());
        return updatedRoom;
    }

    @Override
    public void deleteRoom(Long id) {

    }
}
