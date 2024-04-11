package br.com.eduardo.ponciano.travel.carrental.event;

import org.springframework.context.ApplicationEvent;

import br.com.eduardo.ponciano.travel.commons.model.dto.CarDTO;
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
