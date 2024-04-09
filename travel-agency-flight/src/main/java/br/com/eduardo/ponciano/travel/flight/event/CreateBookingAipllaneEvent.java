package br.com.eduardo.ponciano.travel.flight.event;

import org.springframework.context.ApplicationEvent;

import br.com.eduardo.ponciano.travel.commons.model.dto.AiplaneDTO;
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
