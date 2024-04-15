package br.com.eponciano.ms.booking.car.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.eponciano.ms.booking.car.model.Car;

@Repository
public interface CarRepository extends JpaRepository<Car, Long> {
    // Métodos de consulta personalizados podem ser adicionados aqui, se necessário
}
