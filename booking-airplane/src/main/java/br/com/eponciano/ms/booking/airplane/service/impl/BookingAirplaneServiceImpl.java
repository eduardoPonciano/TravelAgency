package br.com.eponciano.ms.booking.airplane.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.eponciano.ms.booking.airplane.model.BookingAirplane;
import br.com.eponciano.ms.booking.airplane.repository.BookingAirplaneRepository;
import br.com.eponciano.ms.booking.airplane.service.BookingAirplaneService;

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

