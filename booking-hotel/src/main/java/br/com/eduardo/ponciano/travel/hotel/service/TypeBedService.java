package br.com.eduardo.ponciano.travel.hotel.service;

import java.util.List;

import br.com.eduardo.ponciano.travel.hotel.model.TypeBedDTO;

public interface TypeBedService {

	TypeBedDTO saveTypeBed(TypeBedDTO typeBed);
	List<TypeBedDTO> findAll();

}
