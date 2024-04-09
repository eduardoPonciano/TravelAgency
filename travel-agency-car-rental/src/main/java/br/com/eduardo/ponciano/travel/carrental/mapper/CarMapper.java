package br.com.eduardo.ponciano.travel.carrental.mapper;

import br.com.eduardo.ponciano.travel.carrental.model.Car;
import br.com.eduardo.ponciano.travel.commons.model.dto.CarDTO;

public class CarMapper {
	public static Car requestToEntity(CarDTO domain) {

		if (domain != null) {
			Car entity = new Car();
			entity.setCheckinDate(domain.getCheckinDate());
			entity.setCheckoutDate(domain.getCheckoutDate());
			entity.setBrand(domain.getMarca());
			entity.setModel(domain.getModelo());
			entity.setTotalPrice(domain.getTotalPrice());

			return entity;
		}

		return null;

	}
}
