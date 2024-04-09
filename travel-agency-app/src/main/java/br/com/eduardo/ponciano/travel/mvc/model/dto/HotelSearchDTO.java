package br.com.eduardo.ponciano.travel.mvc.model.dto;

import java.time.LocalDate;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class HotelSearchDTO {

    @NotBlank(message = "Cidade deve ser informado")
    @Pattern(regexp = "^(?!\\s*$)[A-Za-z '-]+$", message = "Cidade deve conter somente letras, apóstrofos('), ou hifens(-)")
    private String city;

    @NotNull(message = "Data de inicio deve ser informada")
    @FutureOrPresent(message = "Data de inicio não pode estar no passado")
    private LocalDate checkinDate;

    @NotNull(message = "Data de Saida deve ser informada")
    private LocalDate checkoutDate;
}
