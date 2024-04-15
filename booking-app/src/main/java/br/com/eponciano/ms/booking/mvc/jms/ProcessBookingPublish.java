package br.com.eponciano.ms.booking.mvc.jms;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.fasterxml.jackson.core.JsonProcessingException;

import br.com.eponciano.ms.booking.commons.model.dto.AiplaneDTO;
import br.com.eponciano.ms.booking.commons.model.dto.BookingInitiationDTO;
import br.com.eponciano.ms.booking.commons.model.dto.CarDTO;
import br.com.eponciano.ms.booking.commons.model.dto.ProcesssBookingDTO;
import jakarta.jms.JMSException;
import jakarta.jms.Message;
import jakarta.jms.Session;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProcessBookingPublish {

    @Autowired
    private JmsTemplate jmsTemplate;
	
    public void process(@Valid @ModelAttribute("processDTO") ProcesssBookingDTO processDTO) throws JsonProcessingException {      
    	sendBookingCar(processDTO.getCar());
    	sendBookingAirplane(processDTO.getAirplane());
    	sendBookingHotel(processDTO.getHotel());
    }
    
    public void sendBookingCar(CarDTO carro) throws JsonProcessingException {
        jmsTemplate.send("queue.travel.agency.car.rental", new MessageCreator() {
            @Override
            public Message createMessage(Session session) throws JMSException {
                return session.createObjectMessage(carro);
            }
        });
    }
    
    public void sendBookingAirplane(AiplaneDTO aiplaneDTO) throws JsonProcessingException {
        jmsTemplate.send("queue.travel.agency.flight", new MessageCreator() {
            @Override
            public Message createMessage(Session session) throws JMSException {
                return session.createObjectMessage(aiplaneDTO);
            }
        });
    }
    
    public void sendBookingHotel(BookingInitiationDTO hotelDTO) throws JsonProcessingException {
        jmsTemplate.send("queue.travel.agency.hotel", new MessageCreator() {
            @Override
            public Message createMessage(Session session) throws JMSException {
                return session.createObjectMessage(hotelDTO);
            }
        });
    }
    
    
}
