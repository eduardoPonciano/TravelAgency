package br.com.eduardo.ponciano.travel.mvc.service;

import java.util.List;

import br.com.eduardo.ponciano.travel.commons.model.dto.BookingInitiationDTO;
import br.com.eduardo.ponciano.travel.mvc.model.Booking;
import br.com.eduardo.ponciano.travel.mvc.model.dto.BookingDTO;

public interface BookingService {

    Booking saveBooking(BookingInitiationDTO bookingInitiationDTO, Long customerId);

    BookingDTO confirmBooking(BookingInitiationDTO bookingInitiationDTO, Long customerId);

    List<BookingDTO> findAllBookings();

    BookingDTO findBookingById(Long bookingId);

    List<BookingDTO> findBookingsByCustomerId(Long customerId);

    BookingDTO findBookingByIdAndCustomerId(Long bookingId, Long customerId);

    List<BookingDTO> findBookingsByManagerId(Long managerId);

    BookingDTO findBookingByIdAndManagerId(Long bookingId, Long managerId);

    br.com.eduardo.ponciano.travel.mvc.model.dto.BookingDTO mapBookingModelToBookingDto(Booking booking);

}
