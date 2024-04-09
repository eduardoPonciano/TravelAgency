package br.com.eduardo.ponciano.travel.flight.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.eduardo.ponciano.travel.flight.model.Airplane;
import br.com.eduardo.ponciano.travel.flight.service.AirplaneService;

@RestController
@RequestMapping("/airplane")
public class AirplaneController {

    @Autowired
    private AirplaneService airplaneService;

    @GetMapping
    public List<Airplane> getAllAirplanes() {
        return airplaneService.getAllAirplanes();
    }

    @GetMapping("/{id}")
    public Airplane getAirplaneById(@PathVariable Long id) {
        return airplaneService.getAirplaneById(id);
    }

    @PostMapping
    public Airplane createAirplane(@RequestBody Airplane flight) {
        return airplaneService.saveAirplane(flight);
    }

    @DeleteMapping("/{id}")
    public void deleteAirplaneById(@PathVariable Long id) {
        airplaneService.deleteAirplaneById(id);
    }
}
