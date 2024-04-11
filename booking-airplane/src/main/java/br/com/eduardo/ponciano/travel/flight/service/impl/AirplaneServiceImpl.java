package br.com.eduardo.ponciano.travel.flight.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.eduardo.ponciano.travel.flight.model.Airplane;
import br.com.eduardo.ponciano.travel.flight.repository.AirplaneRepository;
import br.com.eduardo.ponciano.travel.flight.service.AirplaneService;

@Service
public class AirplaneServiceImpl implements AirplaneService  {
    @Autowired
    private AirplaneRepository airplaneRepository;

    public List<Airplane> getAllAirplanes() {
        return airplaneRepository.findAll();
    }

    public Airplane getAirplaneById(Long id) {
        return airplaneRepository.findById(id).orElse(null);
    }

    public Airplane saveAirplane(Airplane flight) {
        return airplaneRepository.save(flight);
    }

    public void deleteAirplaneById(Long id) {
        airplaneRepository.deleteById(id);
    }
}
