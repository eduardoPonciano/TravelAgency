package br.com.eponciano.ms.booking.car.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.eponciano.ms.booking.car.model.Car;
import br.com.eponciano.ms.booking.car.repository.CarRepository;
import br.com.eponciano.ms.booking.car.service.CarService;

@Service
public class CarlServiceImpl implements CarService {
	
    @Autowired
    private CarRepository carRepository;

    public List<Car> getAllCars() {
        return carRepository.findAll();
    }

    public Car getCarById(Long id) {
        return carRepository.findById(id).orElse(null);
    }

    public Car saveCar(Car car) {
        return carRepository.save(car);
    }

    public void deleteCarById(Long id) {
        carRepository.deleteById(id);
    }
}
