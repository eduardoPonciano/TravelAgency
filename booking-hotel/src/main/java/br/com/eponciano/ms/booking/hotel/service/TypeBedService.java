package br.com.eponciano.ms.booking.hotel.service;

import java.util.List;

import br.com.eponciano.ms.booking.hotel.model.TypeBedDTO;

public interface TypeBedService {

	TypeBedDTO saveTypeBed(TypeBedDTO typeBed);
	List<TypeBedDTO> findAll();

}
