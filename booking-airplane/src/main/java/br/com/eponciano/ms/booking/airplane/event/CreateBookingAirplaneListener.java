package br.com.eponciano.ms.booking.airplane.event;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import br.com.eponciano.ms.booking.airplane.mapper.BookingAirplaneMapper;
import br.com.eponciano.ms.booking.airplane.service.BookingAirplaneService;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class CreateBookingAirplaneListener implements ApplicationListener<CreateBookingAipllaneEvent> {
	
	@Autowired
	private BookingAirplaneService userClient;

	@Override
	public void onApplicationEvent(CreateBookingAipllaneEvent event) {
		userClient.saveBookingAirplane(BookingAirplaneMapper.requestToEntity(event.getDto()));
		
	}

}
