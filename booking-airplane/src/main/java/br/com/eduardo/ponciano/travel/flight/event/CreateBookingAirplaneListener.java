package br.com.eduardo.ponciano.travel.flight.event;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import br.com.eduardo.ponciano.travel.flight.mapper.BookingAirplaneMapper;
import br.com.eduardo.ponciano.travel.flight.service.BookingAirplaneService;
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
