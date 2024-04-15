package br.com.eponciano.ms.booking.airplane.service;

import java.util.List;

import org.springframework.stereotype.Service;

import br.com.eponciano.ms.booking.airplane.model.BookingAirplane;

@Service
public interface BookingAirplaneService {

	public List<BookingAirplane> getAllBookingAirplanes();

	public BookingAirplane  getBookingAirplaneById(Long id);

	public BookingAirplane saveBookingAirplane(BookingAirplane flight);

	public void deleteBookingAirplaneById(Long id);
}
