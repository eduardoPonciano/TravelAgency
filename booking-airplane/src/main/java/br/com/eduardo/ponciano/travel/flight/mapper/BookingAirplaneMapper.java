package br.com.eduardo.ponciano.travel.flight.mapper;

import br.com.eduardo.ponciano.travel.commons.model.dto.AiplaneDTO;
import br.com.eduardo.ponciano.travel.flight.model.BookingAirplane;

public class BookingAirplaneMapper {
	public static BookingAirplane requestToEntity(AiplaneDTO domain) {

		if (domain != null) {
			BookingAirplane entity = new BookingAirplane();
			entity.setAssento(domain.getAssento());
			entity.setIdAirplane(domain.getIdAirplane());
			entity.setIdUser(domain.getIdUsuario());
			
			entity.setCheckinDate(domain.getCheckinDate());
			entity.setCheckoutDate(domain.getCheckoutDate());
			entity.setTotalPrice(domain.getTotalPrice());

			return entity;
		}

		return null;

	}
}
