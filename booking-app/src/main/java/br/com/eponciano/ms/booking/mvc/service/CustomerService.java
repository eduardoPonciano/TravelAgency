package br.com.eponciano.ms.booking.mvc.service;

import java.util.Optional;

import br.com.eponciano.ms.booking.mvc.model.Customer;

public interface CustomerService {

    Optional<Customer> findByUserId(Long userId);

    Optional<Customer> findByUsername(String username);
}
