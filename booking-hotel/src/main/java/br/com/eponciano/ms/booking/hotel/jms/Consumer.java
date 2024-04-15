package br.com.eponciano.ms.booking.hotel.jms;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import br.com.eponciano.ms.booking.commons.model.dto.BookingInitiationDTO;
import br.com.eponciano.ms.booking.hotel.event.CreateBookingHotelEvent;

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


