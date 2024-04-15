package br.com.eponciano.ms.booking.airplane.event;

import org.springframework.context.ApplicationEvent;

import br.com.eponciano.ms.booking.commons.model.dto.AiplaneDTO;
import lombok.Getter;

@Getter
public class CreateBookingAipllaneEvent extends ApplicationEvent{
	
	private static final long serialVersionUID = 2691880419232445703L;
	private AiplaneDTO dto ;

	public CreateBookingAipllaneEvent(AiplaneDTO dto) {
		super(dto);
		this.dto = dto;
	}
	

}
