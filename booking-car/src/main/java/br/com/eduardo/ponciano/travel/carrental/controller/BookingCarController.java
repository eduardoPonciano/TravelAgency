package br.com.eduardo.ponciano.travel.carrental.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.eduardo.ponciano.travel.carrental.model.Car;
import br.com.eduardo.ponciano.travel.carrental.service.BookingCarService;

@RestController
@RequestMapping("/bookink/car")
public class BookingCarController {

    @Autowired
    private BookingCarService carRentalService;

    @GetMapping
    public List<Car> getAllCars() {
        return carRentalService.getAllCars();
    }

    @GetMapping("/{id}")
    public Car getCarById(@PathVariable Long id) {
        return carRentalService.getCarById(id);
    }

    @PostMapping
    public Car createCar(@RequestBody Car car) {
        return carRentalService.saveCar(car);
    }

    @DeleteMapping("/{id}")
    public void deleteCar(@PathVariable Long id) {
        carRentalService.deleteCarById(id);
    }
}
