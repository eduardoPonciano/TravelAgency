package br.com.eponciano.ms.booking.airplane.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.eponciano.ms.booking.airplane.model.Airplane;
import br.com.eponciano.ms.booking.airplane.repository.AirplaneRepository;
import br.com.eponciano.ms.booking.airplane.service.AirplaneService;

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
