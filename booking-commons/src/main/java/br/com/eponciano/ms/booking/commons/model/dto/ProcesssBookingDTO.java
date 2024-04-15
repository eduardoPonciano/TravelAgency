package br.com.eponciano.ms.booking.commons.model.dto;

import java.util.ArrayList;
import java.util.List;

import jakarta.validation.Valid;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProcesssBookingDTO {

    private Long id;

    @Valid
    private CarDTO car;
    
    @Valid
    private AiplaneDTO airplane;

    @Valid
    private BookingInitiationDTO hotel;

    @Valid
    private List<RoomDTO> roomDTOs = new ArrayList<>();

    private String managerUsername;

}
