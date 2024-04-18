package br.com.eponciano.ms.booking.commons.model.dto;

import java.math.BigDecimal;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RoomDTO {

    private Long id;

    private Long hotelId;

    private Long typeBedId;

    @NotNull(message = "Quantidade de quartos deve ser informado")
    @PositiveOrZero(message = "Quantidade de quartos deve ser maior ou igual a zero")
    private Integer roomCount;
    
    //TODO remover
    @Enumerated(EnumType.STRING)
    private RoomType roomType;

    @NotNull(message = "o Preço deve ser informado")
    @PositiveOrZero(message = "Preço da diaria deve ser maior ou igual a zero")
    private BigDecimal dailyPrice;

}