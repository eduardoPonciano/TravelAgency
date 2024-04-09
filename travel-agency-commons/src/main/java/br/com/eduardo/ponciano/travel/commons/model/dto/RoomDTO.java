package br.com.eduardo.ponciano.travel.commons.model.dto;

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

    private RoomType roomType;

    @NotNull(message = "Quantidade de quartos deve ser informado")
    @PositiveOrZero(message = "Quantidade de quartos deve ser maior ou igual a zero")
    private Integer roomCount;

    @NotNull(message = "o Preço deve ser informado")
    @PositiveOrZero(message = "Preço da diaria deve ser maior ou igual a zero")
    private Double pricePerNight;

}