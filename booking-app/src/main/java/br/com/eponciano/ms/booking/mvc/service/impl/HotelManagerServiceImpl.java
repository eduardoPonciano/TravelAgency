package br.com.eponciano.ms.booking.mvc.service.impl;

import org.springframework.stereotype.Service;

import br.com.eponciano.ms.booking.mvc.model.HotelManager;
import br.com.eponciano.ms.booking.mvc.model.User;
import br.com.eponciano.ms.booking.mvc.repository.HotelManagerRepository;
import br.com.eponciano.ms.booking.mvc.service.HotelManagerService;
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
