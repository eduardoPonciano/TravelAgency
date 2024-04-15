package br.com.eponciano.ms.booking.car.event;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import br.com.eponciano.ms.booking.car.mapper.CarMapper;
import br.com.eponciano.ms.booking.car.service.BookingCarService;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class CreateCarListener implements ApplicationListener<CreateCarEvent> {
	
	@Autowired
	private BookingCarService userClient;

	@Override
	public void onApplicationEvent(CreateCarEvent event) {
		userClient.saveCar(CarMapper.requestToEntity(event.getCarro()));
		
	}

}
