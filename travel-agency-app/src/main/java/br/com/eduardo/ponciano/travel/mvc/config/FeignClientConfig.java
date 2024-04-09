package br.com.eduardo.ponciano.travel.mvc.config;

import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableFeignClients(basePackages = {"br.com.eduardo.ponciano.travel.mvc.client"})
public class FeignClientConfig {

}