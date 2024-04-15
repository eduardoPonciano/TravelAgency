package br.com.eponciano.ms.booking.hotel.service;

import java.util.List;
import java.util.Optional;

import br.com.eduardo.ponciano.travel.commons.model.dto.RoomDTO;
import br.com.eponciano.ms.booking.hotel.model.Room;

public interface RoomService {

    Room saveRoom(RoomDTO roomDTO);

    List<Room> saveRooms(List<RoomDTO> roomDTOs);

    Optional<Room> findRoomById(Long id);

    List<Room> findRoomsByHotelId(Long roomId);

    Room updateRoom(RoomDTO roomDTO);

    void deleteRoom(Long id);

}
