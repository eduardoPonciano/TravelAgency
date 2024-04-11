package br.com.eponciano.ms.booking.mvc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.eponciano.ms.booking.mvc.model.Payment;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long> {
}
