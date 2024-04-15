package br.com.eponciano.ms.booking.hotel.mapper;

import br.com.eduardo.ponciano.travel.commons.model.dto.RoomDTO;
import br.com.eponciano.ms.booking.hotel.model.Hotel;
import br.com.eponciano.ms.booking.hotel.model.Room;
import br.com.eponciano.ms.booking.hotel.model.TypeBed;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class RoomMapper {

    public static Room dtoToEntity(RoomDTO roomDTO) {
        log.debug("Mapping RoomDTO to Room: {}", roomDTO);
        Room room = Room.builder()
        		.id(roomDTO.getId())
                .hotel(roomDTO.getHotelId()!=null?
                 Hotel.builder().id(roomDTO.getHotelId()).build():null)
                .typeBed(roomDTO.getHotelId()!=null?
                		TypeBed.builder().id(roomDTO.getTypeBedId()).build():null)
                .count(roomDTO.getRoomCount())
                .dailyPrice(roomDTO.getDailyPrice())
                .build();
        log.debug("Mapped Room: {}", room);
        return room;

    }

    public static RoomDTO entityToDTO(Room room) {
        log.debug("Mapping Room to RoomDTO: {}", room);
         RoomDTO roomDTO = RoomDTO.builder()
                .id(room.getId())
                .hotelId(room.getHotel().getId())
                .dailyPrice(room.getDailyPrice())
                .roomCount(room.getCount())
                .typeBedId(room.getTypeBed().getId())
                .build();
        log.debug("Mapped RoomDTO: {}", room);
        return roomDTO;
    }
}
