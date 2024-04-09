package br.com.eduardo.ponciano.travel.mvc.service.impl;

import org.springframework.stereotype.Service;

import br.com.eduardo.ponciano.travel.mvc.model.HotelManager;
import br.com.eduardo.ponciano.travel.mvc.model.User;
import br.com.eduardo.ponciano.travel.mvc.repository.HotelManagerRepository;
import br.com.eduardo.ponciano.travel.mvc.service.HotelManagerService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class HotelManagerServiceImpl implements HotelManagerService {

    private final HotelManagerRepository hotelManagerRepository;

    @Override
    public HotelManager findByUser(User user) {
        log.info("Attempting to find HotelManager for user ID: {}", user.getId());
        return hotelManagerRepository.findByUser(user)
                .orElseThrow(() -> new EntityNotFoundException("HotelManager not found for user " + user.getUsername()));
    }
}
