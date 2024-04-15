package br.com.eponciano.ms.booking.car.service;

import java.util.List;

import org.springframework.stereotype.Service;

import br.com.eponciano.ms.booking.car.model.Car;

@Service
public interface BookingCarService {

    public List<Car> getAllCars();

    public Car getCarById(Long id);

    public Car saveCar(Car car);

    public void deleteCarById(Long id);
}

