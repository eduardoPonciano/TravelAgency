package br.com.eponciano.ms.booking.commons.model.dto;

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
public class CarDTO  implements Serializable{
	private static final long serialVersionUID = -2168201323546738633L;
	private Long id;
	private String modelo;
    private String marca;
    private LocalDate checkinDate;
    private LocalDate checkoutDate;
    private BigDecimal totalPrice;
}
