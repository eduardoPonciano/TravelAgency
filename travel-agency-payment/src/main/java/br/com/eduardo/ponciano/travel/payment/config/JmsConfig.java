package br.com.eduardo.ponciano.travel.payment.config;
import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.annotation.EnableJms;

@Configuration
@EnableJms
public class JmsConfig {
	@Bean
    public ActiveMQQueue paymentUserQueue() {
        return new ActiveMQQueue(Destinations.PROCESS_PAYMENT);
    }
}

