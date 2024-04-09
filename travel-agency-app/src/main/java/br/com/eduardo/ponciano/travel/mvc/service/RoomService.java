package br.com.eduardo.ponciano.travel.mvc.service;

import java.util.List;
import java.util.Optional;

import br.com.eduardo.ponciano.travel.commons.model.dto.RoomDTO;
import br.com.eduardo.ponciano.travel.mvc.model.Hotel;
import br.com.eduardo.ponciano.travel.mvc.model.Room;

public interface RoomService {

    Room saveRoom(RoomDTO roomDTO, Hotel hotel);

    List<Room> saveRooms(List<RoomDTO> roomDTOs, Hotel hotel);

    Optional<Room> findRoomById(Long id);

    List<Room> findRoomsByHotelId(Long hotelId);

    Room updateRoom(RoomDTO roomDTO);

    void deleteRoom(Long id);

    Room mapRoomDtoToRoom(RoomDTO roomDTO, Hotel hotel);

    RoomDTO mapRoomToRoomDto(Room room);

}
