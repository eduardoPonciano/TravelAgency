package br.com.eponciano.ms.booking.hotel.model;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TypeBedDTO {
	
    private Long id;
    @NotBlank(message = "Descrição deve ser informado")
    private String description;   
    @NotBlank(message = "Capacidade deve ser informado") 
    private Integer capacity;
    
	

}
