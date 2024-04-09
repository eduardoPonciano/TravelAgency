package br.com.eduardo.ponciano.travel.commons.model.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AiplaneDTO implements Serializable{
	private static final long serialVersionUID = -7842336426013737462L;
	private Long idAirplane;
	private Long idUsuario;
	private String horario;
    private String companhia;
    private String numeroAssento;
    private LocalDate checkinDate;
    private LocalDate checkoutDate;
    private BigDecimal totalPrice;
}