package br.com.eponciano.ms.booking.mvc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"br.com.eponciano.ms.booking.mvc"})
public class TravelAgencyMvcApplication {

    public static void main(String[] args) {SpringApplication.run(TravelAgencyMvcApplication.class, args);
    }

}
