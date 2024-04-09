package br.com.eduardo.ponciano.travel.mvc.model.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import br.com.eduardo.ponciano.travel.commons.model.dto.RoomDTO;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class HotelAvailabilityDTO implements Serializable{

    private static final long serialVersionUID = -488420882714754071L;

	private Long id;

    private String name;

    private AddressDTO addressDTO;

    private List<RoomDTO> roomDTOs = new ArrayList<>();

    private Integer maxAvailableSingleRooms;

    private Integer maxAvailableDoubleRooms;

}
