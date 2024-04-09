package br.com.eduardo.ponciano.travel.mvc.service;

import java.util.Optional;

import br.com.eduardo.ponciano.travel.mvc.model.Customer;

public interface CustomerService {

    Optional<Customer> findByUserId(Long userId);

    Optional<Customer> findByUsername(String username);
}
