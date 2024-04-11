package br.com.eduardo.ponciano.travel.carrental.mapper;

import br.com.eduardo.ponciano.travel.carrental.model.Car;
import br.com.eduardo.ponciano.travel.commons.model.dto.CarDTO;

public class CarMapper {
	public static Car requestToEntity(CarDTO domain) {

		if (domain != null) {
			Car entity = new Car();
			entity.setBrand(null);
			entity.setColor(null);
			entity.setDailyPrice(null);
			entity.setFuelType(null);
			entity.setLicensePlate(null);
			entity.setModel(null);
			entity.setYear(0);
			return entity;
		}

		return null;

	}
}
