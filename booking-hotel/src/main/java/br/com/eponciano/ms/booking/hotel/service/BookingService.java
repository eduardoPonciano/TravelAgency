package br.com.eponciano.ms.booking.hotel.service;

import java.util.List;

import br.com.eduardo.ponciano.travel.commons.model.dto.BookingDTO;
import br.com.eduardo.ponciano.travel.commons.model.dto.BookingInitiationDTO;
import br.com.eponciano.ms.booking.hotel.model.Booking;

public interface BookingService {

    Booking saveBooking(BookingInitiationDTO bookingInitiationDTO, Long customerId);

    BookingDTO confirmBooking(BookingInitiationDTO bookingInitiationDTO, Long customerId);

    List<BookingDTO> findAllBookings();

    BookingDTO findBookingById(Long bookingId);

    List<BookingDTO> findBookingsByCustomerId(Long customerId);

    BookingDTO findBookingByIdAndCustomerId(Long bookingId, Long customerId);

    BookingDTO mapBookingModelToBookingDto(Booking booking);

}
