package br.com.eduardo.ponciano.travel.hotel.event;

import org.springframework.context.ApplicationEvent;

import br.com.eduardo.ponciano.travel.commons.model.dto.BookingInitiationDTO;
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
