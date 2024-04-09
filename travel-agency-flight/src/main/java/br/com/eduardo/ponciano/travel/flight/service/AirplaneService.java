package br.com.eduardo.ponciano.travel.flight.service;

import java.util.List;

import org.springframework.stereotype.Service;

import br.com.eduardo.ponciano.travel.flight.model.Airplane;

@Service
public interface AirplaneService {

	public List<Airplane> getAllAirplanes();

	public Airplane getAirplaneById(Long id);

	public Airplane saveAirplane(Airplane flight);

	public void deleteAirplaneById(Long id);
}
