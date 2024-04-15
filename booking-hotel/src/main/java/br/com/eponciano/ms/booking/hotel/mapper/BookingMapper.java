package br.com.eponciano.ms.booking.hotel.mapper;

import br.com.eponciano.ms.booking.commons.model.dto.BookingDTO;
import br.com.eponciano.ms.booking.hotel.model.Booking;

public class BookingMapper {
	public static Booking requestToEntity(BookingDTO domain) {

		if (domain != null) {
			Booking entity = new Booking();
			entity.setBookingDate(domain.getBookingDate());
			entity.setCheckinDate(domain.getCheckinDate());
			entity.setCheckoutDate(domain.getCheckoutDate());
			entity.setConfirmationNumber(domain.getConfirmationNumber());
			
			domain.getNumeroQuarto();
			domain.getTotalPrice();
			
			return entity;
		}

		return null;

	}
}
