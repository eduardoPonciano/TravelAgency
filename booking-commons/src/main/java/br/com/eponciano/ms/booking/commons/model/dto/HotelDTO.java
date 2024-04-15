package br.com.eponciano.ms.booking.commons.model.dto;

import java.util.ArrayList;
import java.util.List;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class HotelDTO {

    private Long id;

    @NotBlank(message = "Hotel deve ser informado")
    @Pattern(regexp = "^(?!\\s*$)[A-Za-zÀ-ú0-9\\s]+$", message = "Hotel  deve conter somente letras e numeros")
    private String name;

    @Valid
    private AddressDTO addressDTO;

    @Valid
    private List<RoomDTO> roomDTOs = new ArrayList<>();

    private String managerUsername;

}
