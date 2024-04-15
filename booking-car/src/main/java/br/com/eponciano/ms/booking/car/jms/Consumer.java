package br.com.eponciano.ms.booking.car.jms;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import br.com.eponciano.ms.booking.car.event.CreateCarEvent;
import br.com.eponciano.ms.booking.commons.model.dto.CarDTO;

@Component
public class Consumer {

	@Autowired
	private ApplicationEventPublisher publisher;

    @JmsListener(destination = "queue.travel.agency.car.rental")
    public void listener(CarDTO carDto) {
        System.out.println("Messagem recebida: " + carDto);
        publisher.publishEvent(new CreateCarEvent(carDto));
    }
    
}


