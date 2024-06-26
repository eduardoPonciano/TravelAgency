package br.com.eponciano.ms.booking.mvc.config;

import java.util.Arrays;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JmsConfig {
	@Bean
	public ActiveMQConnectionFactory activeMQConnectionFactory() {
	    ActiveMQConnectionFactory factory = new ActiveMQConnectionFactory("tcp://localhost:61616");
	    factory.setTrustedPackages(Arrays.asList("br.com.eponciano.ms.booking.commons.model.dto"));
	    return factory;
	}
}
