package br.com.eponciano.ms.booking.hotel.event;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import br.com.eponciano.ms.booking.hotel.service.BookingService;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class CreateBookingHotelListener implements ApplicationListener<CreateBookingHotelEvent> {
	
	@Autowired
	private BookingService hotelClient;

	@Override
	public void onApplicationEvent(CreateBookingHotelEvent event) {
		hotelClient.saveBooking(event.getBooking(),event.getBooking().getCustomerId());
		
	}

}
