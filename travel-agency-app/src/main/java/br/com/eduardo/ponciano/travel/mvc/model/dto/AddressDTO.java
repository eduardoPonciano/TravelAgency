package br.com.eduardo.ponciano.travel.mvc.model.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AddressDTO {

    private Long id;

    @NotBlank(message = "Endereço deve ser informado")
    @Pattern(regexp = "^[A-Za-zÀ-ú0-9 .,:-]*$", message = "Endereço deve conter somente letras, numeros, e alguns caracteres epeciais(. , : - )")
    private String addressLine;
    @NotBlank(message = "Cidade deve ser informado")
    @Pattern(regexp = "^(?!\\s*$)[A-Za-zÀ-ú ]+$", message = "Cidade  deve conter somente letras")
    private String city;

    @NotBlank(message = "Pais deve ser informado")
    @Pattern(regexp = "^(?!\\s*$)[A-Za-zÀ-ú ]+$", message = "Pais deve conter somente letras")
    private String country;
}
