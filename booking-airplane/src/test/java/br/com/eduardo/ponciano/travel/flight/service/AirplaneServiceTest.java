package br.com.eduardo.ponciano.travel.flight.service;

import java.util.List;

import org.springframework.stereotype.Service;

import br.com.eponciano.ms.booking.airplane.model.Airplane;

@Service
public interface AirplaneServiceTest {

    public List<Airplane> getAllCars();

    public Airplane getCarById(Long id);

    public Airplane saveCar(Airplane car);

    public void deleteCarById(Long id);
}

