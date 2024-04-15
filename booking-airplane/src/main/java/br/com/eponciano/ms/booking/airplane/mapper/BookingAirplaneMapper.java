package br.com.eponciano.ms.booking.airplane.mapper;

import br.com.eponciano.ms.booking.airplane.model.BookingAirplane;
import br.com.eponciano.ms.booking.commons.model.dto.AiplaneDTO;

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
