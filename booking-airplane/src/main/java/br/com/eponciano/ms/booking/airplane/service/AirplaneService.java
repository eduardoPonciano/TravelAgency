package br.com.eponciano.ms.booking.airplane.service;

import java.util.List;

import org.springframework.stereotype.Service;

import br.com.eponciano.ms.booking.airplane.model.Airplane;

@Service
public interface AirplaneService {

	public List<Airplane> getAllAirplanes();

	public Airplane getAirplaneById(Long id);

	public Airplane saveAirplane(Airplane flight);

	public void deleteAirplaneById(Long id);
}
