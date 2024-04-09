package br.com.eduardo.ponciano.travel.mvc.service.impl;

import java.util.Optional;

import org.springframework.stereotype.Service;

import br.com.eduardo.ponciano.travel.mvc.model.Customer;
import br.com.eduardo.ponciano.travel.mvc.repository.CustomerRepository;
import br.com.eduardo.ponciano.travel.mvc.service.CustomerService;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;

    @Override
    public Optional<Customer> findByUserId(Long userId) {
        return customerRepository.findByUserId(userId);
    }

    @Override
    public Optional<Customer> findByUsername(String username) {
        return customerRepository.findByUsername(username);
    }

}
