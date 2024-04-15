package br.com.eponciano.ms.booking.car.mapper;

import br.com.eponciano.ms.booking.car.model.Car;
import br.com.eponciano.ms.booking.commons.model.dto.CarDTO;

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
