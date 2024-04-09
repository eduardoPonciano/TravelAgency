package br.com.eduardo.ponciano.travel.mvc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@SpringBootApplication
@ComponentScan(basePackages = {"br.com.eduardo.ponciano.travel.mvc"})
public class TravelAgencyMvcApplication {

    public static void main(String[] args) {SpringApplication.run(TravelAgencyMvcApplication.class, args);
    }

}
