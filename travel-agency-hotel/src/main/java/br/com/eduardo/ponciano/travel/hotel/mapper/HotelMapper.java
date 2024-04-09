package br.com.eduardo.ponciano.travel.hotel.mapper;

import br.com.eduardo.ponciano.travel.commons.model.dto.BookingDTO;
import br.com.eduardo.ponciano.travel.hotel.model.Hotel;

//TODO criar dto, mapper e alterar persistencia
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
}
