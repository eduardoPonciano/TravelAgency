package br.com.eduardo.ponciano.travel.hotel.mapper;

import br.com.eduardo.ponciano.travel.commons.model.dto.BookingDTO;
import br.com.eduardo.ponciano.travel.hotel.model.Address;

//TODO criar dto, mapper e alterar persistencia
public class AddressMapper {
	public static Address requestToEntity(BookingDTO domain) {

		if (domain != null) {
			Address entity = new Address();
			entity.setAddressLine(null);
			entity.setCity(null);
			entity.setCountry(null);
			return entity;
		}

		return null;

	}
}
