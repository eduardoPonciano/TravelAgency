package br.com.eduardo.ponciano.travel.carrental.service;

import java.util.List;

import org.springframework.stereotype.Service;

import br.com.eduardo.ponciano.travel.carrental.model.Car;

@Service
public interface CarRentalService {

    public List<Car> getAllCars();

    public Car getCarById(Long id);

    public Car saveCar(Car car);

    public void deleteCarById(Long id);
}

