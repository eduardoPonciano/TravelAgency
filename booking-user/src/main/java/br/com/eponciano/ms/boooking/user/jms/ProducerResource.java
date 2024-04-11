package br.com.eponciano.ms.boooking.user.jms;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test/publish")
public class ProducerResource {

    @Autowired
    private JmsTemplate jmsTemplate;


    @GetMapping("/{message}")
    public String publish(@PathVariable("message") final String message) {

        jmsTemplate.convertAndSend("queue.travel.agency.user", message);

        return "Published Successfully";
    }
}