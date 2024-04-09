package br.com.eduardo.ponciano.travel.user.jms;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class Consumer {

    @JmsListener(destination = "queue.travel.agency.user")
    public void listener(String message) {
        System.out.println("Messagem recebida: " + message);
    }
    
}


