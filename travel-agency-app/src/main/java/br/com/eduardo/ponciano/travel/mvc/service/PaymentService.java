package br.com.eduardo.ponciano.travel.mvc.service;

import br.com.eduardo.ponciano.travel.commons.model.dto.BookingInitiationDTO;
import br.com.eduardo.ponciano.travel.mvc.model.Booking;
import br.com.eduardo.ponciano.travel.mvc.model.Payment;

public interface PaymentService {

    Payment savePayment(BookingInitiationDTO bookingInitiationDTO, Booking booking);
}
