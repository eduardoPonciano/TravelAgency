package br.com.eduardo.ponciano.travel.mvc.mapper;

import br.com.eduardo.ponciano.travel.commons.model.dto.BookingInitiationDTO;
import br.com.eduardo.ponciano.travel.commons.model.dto.ProcesssBookingDTO;
import br.com.eduardo.ponciano.travel.mvc.model.dto.BookingDTO;

public class ProcesssBookingMapper {

	public static ProcesssBookingDTO initiationToProcess(BookingDTO domain) {

		BookingInitiationDTO dtoBookingDTO = BookingInitiationDTO.builder()
		.hotelId(domain.getHotelId())
		.checkinDate(domain.getCheckinDate())
		.checkoutDate(domain.getCheckoutDate())
		.customerId(domain.getCustomerId())
		.totalPrice(domain.getTotalPrice())
		.build();
		
		 return ProcesssBookingDTO.builder().hotel(dtoBookingDTO).build();
	}
}
