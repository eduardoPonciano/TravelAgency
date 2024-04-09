package br.com.eduardo.ponciano.travel.flight.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.eduardo.ponciano.travel.flight.model.BookingAirplane;

@Repository
public interface BookingAirplaneRepository extends JpaRepository<BookingAirplane, Long> {
    // Métodos de consulta personalizados podem ser adicionados aqui, se necessário
}

