package br.com.eponciano.ms.booking.airplane.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.eponciano.ms.booking.airplane.model.BookingAirplane;

@Repository
public interface BookingAirplaneRepository extends JpaRepository<BookingAirplane, Long> {
    // Métodos de consulta personalizados podem ser adicionados aqui, se necessário
}

