package br.com.eduardo.ponciano.travel.flight.jms;
import java.util.Arrays;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.annotation.EnableJms;

@Configuration
@EnableJms
public class JmsConfig {

	@Bean
    public ActiveMQQueue agencyFlightQueue() {
        return new ActiveMQQueue("queue-travel.agency.flight");
    }	

	@Bean
	public ActiveMQConnectionFactory activeMQConnectionFactory() {
	    ActiveMQConnectionFactory factory = new ActiveMQConnectionFactory("tcp://localhost:61616");
	    factory.setTrustedPackages(Arrays.asList("br.com.eduardo.ponciano.travel.commons.model.dto", "java"));
	    return factory;
	}
}

