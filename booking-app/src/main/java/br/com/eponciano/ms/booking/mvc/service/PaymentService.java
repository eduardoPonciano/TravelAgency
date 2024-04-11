package br.com.eponciano.ms.booking.mvc.service;

import br.com.eduardo.ponciano.travel.commons.model.dto.BookingInitiationDTO;
import br.com.eponciano.ms.booking.mvc.model.Booking;
import br.com.eponciano.ms.booking.mvc.model.Payment;

public interface PaymentService {

    Payment savePayment(BookingInitiationDTO bookingInitiationDTO, Booking booking);
}
