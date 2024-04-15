package br.com.eponciano.ms.booking.hotel.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.util.StringUtils;

import br.com.eponciano.ms.booking.commons.model.dto.AddressDTO;
import br.com.eponciano.ms.booking.commons.model.dto.BookingDTO;
import br.com.eponciano.ms.booking.commons.model.dto.HotelDTO;
import br.com.eponciano.ms.booking.commons.model.dto.HotelRegistrationDTO;
import br.com.eponciano.ms.booking.commons.model.dto.RoomDTO;
import br.com.eponciano.ms.booking.hotel.model.Address;
import br.com.eponciano.ms.booking.hotel.model.Hotel;
import br.com.eponciano.ms.booking.hotel.model.Room;

public class HotelMapper {
	public static Hotel requestToEntity(BookingDTO domain) {

		if (domain != null) {
			Hotel entity = new Hotel();
			entity.setName(null);
			entity.setRooms(null);
			entity.setAddress(null);
			return entity;
		}

		return null;

	}
	
    public static Hotel registrationDTOToEntity(HotelRegistrationDTO dto) {
        return Hotel.builder()
                .name(formatText(dto.getName()))
                .managerId(dto.getManagerId())
                .build();
    }

    public static HotelDTO entityToDTO(Hotel hotel) {
        List<RoomDTO> roomDTOs = hotel.getRooms().stream()
                .map(room -> RoomMapper.entityToDTO(room))
                .collect(Collectors.toList()); 

        AddressDTO addressDTO = AddressMapper.entityToDTO(hotel.getAddress());

        return HotelDTO.builder()
                .id(hotel.getId())
                .name(hotel.getName())
                .addressDTO(addressDTO)
                .roomDTOs(roomDTOs)
                .build();
    }
    
    public static Hotel  dtoToEntity(HotelDTO hotel) {
        List<Room> rooms = hotel.getRoomDTOs().stream()
                .map(room -> RoomMapper.dtoToEntity(room))
                .collect(Collectors.toList()); 

        Address address = AddressMapper.dtoToEntity(hotel.getAddressDTO());

        return Hotel.builder()
                .id(hotel.getId())
                .name(hotel.getName())
                .address(address)
                .rooms(rooms)
                .build();
    }

    private static String formatText(String text) {
        return StringUtils.capitalize(text.trim());
    }
}
