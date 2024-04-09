package br.com.eduardo.ponciano.travel.flight.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.eduardo.ponciano.travel.flight.model.BookingAirplane;
import br.com.eduardo.ponciano.travel.flight.repository.BookingAirplaneRepository;
import br.com.eduardo.ponciano.travel.flight.service.BookingAirplaneService;

@Service
public class BookingAirplaneServiceImpl implements BookingAirplaneService  {
	
    @Autowired
    private BookingAirplaneRepository bookingAirplaneRepository;

    public List<BookingAirplane> getAllBookingAirplanes() {
        return bookingAirplaneRepository.findAll();
    }

    public BookingAirplane getBookingAirplaneById(Long id) {
        return bookingAirplaneRepository.findById(id).orElse(null);
    }

    public BookingAirplane saveBookingAirplane(BookingAirplane flight) {
        return bookingAirplaneRepository.save(flight);
    }

    public void deleteBookingAirplaneById(Long id) {
        bookingAirplaneRepository.deleteById(id);
    }
}

