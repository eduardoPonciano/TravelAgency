package br.com.eduardo.ponciano.travel.carrental.mapper;

import br.com.eduardo.ponciano.travel.carrental.model.BookingCar;
import br.com.eduardo.ponciano.travel.commons.model.dto.CarDTO;

public class BookingCarMapper {
	public static BookingCar requestToEntity(CarDTO domain) {

		if (domain != null) {
			BookingCar entity = new BookingCar();
			entity.setIdUser(null);
			entity.setCar(null);
			entity.setConfirmationNumber(null);
			entity.setBookingDate(null);
			entity.setCheckinDate(domain.getCheckinDate());
			entity.setCheckoutDate(domain.getCheckoutDate());
			entity.setTotalPrice(domain.getTotalPrice());

			return entity;
		}

		return null;

	}
}
