package br.com.eponciano.ms.booking.mvc.service.impl;

import org.springframework.stereotype.Service;

import br.com.eponciano.ms.booking.commons.model.dto.BookingInitiationDTO;
import br.com.eponciano.ms.booking.mvc.model.Booking;
import br.com.eponciano.ms.booking.mvc.model.Payment;
import br.com.eponciano.ms.booking.mvc.model.enums.Currency;
import br.com.eponciano.ms.booking.mvc.model.enums.PaymentMethod;
import br.com.eponciano.ms.booking.mvc.model.enums.PaymentStatus;
import br.com.eponciano.ms.booking.mvc.repository.PaymentRepository;
import br.com.eponciano.ms.booking.mvc.service.PaymentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class PaymentServiceImpl implements PaymentService {

    private final PaymentRepository paymentRepository;

    @Override
    public Payment savePayment(BookingInitiationDTO bookingInitiationDTO, Booking booking) {
        Payment payment = Payment.builder()
                .booking(booking)
                .totalPrice(bookingInitiationDTO.getTotalPrice())
                .paymentStatus(PaymentStatus.COMPLETED) // Assuming the payment is completed
                .paymentMethod(PaymentMethod.CREDIT_CARD) // Default to CREDIT_CARD
                .currency(Currency.USD) // Default to USD
                .build();

        Payment savedPayment = paymentRepository.save(payment);
        log.info("Payment saved with transaction ID: {}", savedPayment.getTransactionId());

        return savedPayment;
    }
}
