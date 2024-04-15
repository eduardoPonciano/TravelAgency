package br.com.eponciano.ms.booking.car.event;

import org.springframework.context.ApplicationEvent;

import br.com.eponciano.ms.booking.commons.model.dto.CarDTO;
import lombok.Getter;

@Getter
public class CreateCarEvent extends ApplicationEvent{
	
	private static final long serialVersionUID = -3510802758110530487L;
	private CarDTO carro ;

	public CreateCarEvent(CarDTO carro) {
		super(carro);
		this.carro = carro;
	}
	

}
