package br.com.eduardo.ponciano.travel.hotel.jms;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import br.com.eduardo.ponciano.travel.commons.model.dto.BookingInitiationDTO;
import br.com.eduardo.ponciano.travel.hotel.event.CreateBookingHotelEvent;

@Component
public class Consumer {

	@Autowired
	private ApplicationEventPublisher publisher;
	
    @JmsListener(destination = "queue.travel.agency.hotel")
    public void listener(BookingInitiationDTO bookingDTO) {
        System.out.println("Messagem recebida: " + bookingDTO);
        publisher.publishEvent(new CreateBookingHotelEvent(bookingDTO));
    }
    
}


