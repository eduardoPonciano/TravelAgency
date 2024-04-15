package br.com.eponciano.ms.booking.airplane.jms;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import br.com.eponciano.ms.booking.airplane.event.CreateBookingAipllaneEvent;
import br.com.eponciano.ms.booking.commons.model.dto.AiplaneDTO;

@Component
public class Consumer {

	@Autowired
	private ApplicationEventPublisher publisher;
	
    @JmsListener(destination = "queue.travel.agency.flight")
    public void listener(AiplaneDTO aiplaneDTO) {
        System.out.println("Messagem recebida: " + aiplaneDTO);
        publisher.publishEvent(new CreateBookingAipllaneEvent(aiplaneDTO));
    }
    
}


