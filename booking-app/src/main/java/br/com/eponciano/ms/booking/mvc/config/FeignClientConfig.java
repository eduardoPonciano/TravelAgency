package br.com.eponciano.ms.booking.mvc.config;

import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableFeignClients(basePackages = {"br.com.eponciano.ms.booking.mvc.client"})
public class FeignClientConfig {

}
