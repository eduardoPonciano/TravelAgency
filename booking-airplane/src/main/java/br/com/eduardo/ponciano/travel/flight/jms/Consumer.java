package br.com.eduardo.ponciano.travel.flight.jms;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import br.com.eduardo.ponciano.travel.commons.model.dto.AiplaneDTO;
import br.com.eduardo.ponciano.travel.flight.event.CreateBookingAipllaneEvent;

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


