package br.com.eponciano.ms.booking.hotel.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.eponciano.ms.booking.commons.model.dto.RoomDTO;
import br.com.eponciano.ms.booking.hotel.model.Room;
import br.com.eponciano.ms.booking.hotel.service.RoomService;

@RestController
@RequestMapping
public class RoomController {

    @Autowired
    private RoomService roomService;

    @PostMapping("/room")
    public Room saveHotel(@RequestBody RoomDTO room) {
    	return roomService.saveRoom(room);
    }
    
    @GetMapping("/room/{id}")
    public Optional<Room> findRoomById(@PathVariable Long id) {
    	return roomService.findRoomById(id);
    }
    
    @PutMapping("/room")
    public Room updateRoom(@RequestBody RoomDTO roomDTO) {
    	return roomService.updateRoom(roomDTO);
    }

    @DeleteMapping("/room")
    public void deleteRoom(@RequestBody Long id) {
    	roomService.deleteRoom(id);
    }
    
    @PostMapping("/rooms")
    public List<Room> saveRooms(@RequestBody List<RoomDTO> roomDTOs) {
    	return roomService.saveRooms(roomDTOs);
    }
    
    @GetMapping("/rooms/id/{id}")
    public  List<Room> findRoomsByHotelId(@PathVariable Long id) {
    	return roomService.findRoomsByHotelId(id);
    }

}
