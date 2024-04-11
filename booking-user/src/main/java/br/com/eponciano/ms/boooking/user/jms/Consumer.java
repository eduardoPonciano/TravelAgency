package br.com.eponciano.ms.boooking.user.jms;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class Consumer {

    @JmsListener(destination = "queue.travel.agency.user")
    public void listener(String message) {
        System.out.println("Messagem recebida: " + message);
    }
    
}


