package br.com.eduardo.ponciano.travel.commons.model.dto;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonAlias;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class HotelRegistrationDTO {

    @NotBlank(message = "Hotel deve ser informado")
    @Pattern(regexp = "^(?!\\s*$)[A-Za-zÀ-ú0-9\\s]+$", message = "Hotel  deve conter somente letras e numeros")
    private String name;
    
    private Long managerId;

    @Valid
    @JsonAlias("address")
    private AddressDTO addressDTO;

    @Valid
    private List<RoomDTO> roomDTOs = new ArrayList<>();

}
