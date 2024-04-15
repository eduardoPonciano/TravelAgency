package br.com.eponciano.ms.booking.hotel.event;

import org.springframework.context.ApplicationEvent;

import br.com.eponciano.ms.booking.commons.model.dto.BookingInitiationDTO;
import lombok.Getter;

@Getter
public class CreateBookingHotelEvent extends ApplicationEvent{
	
	private static final long serialVersionUID = -3510802758110530487L;
	private BookingInitiationDTO booking ;

	public CreateBookingHotelEvent(BookingInitiationDTO booking) {
		super(booking);
		this.booking = booking;
	}
	

}
