package br.com.eduardo.ponciano.travel.payment.jms;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import br.com.eduardo.ponciano.travel.payment.config.Destinations;

@Component
public class Consumer {

    @JmsListener(destination = Destinations.PROCESS_PAYMENT)
    public void listener(String message) {
        System.out.println("Messagem recebida: " + message);
    }
    
}
