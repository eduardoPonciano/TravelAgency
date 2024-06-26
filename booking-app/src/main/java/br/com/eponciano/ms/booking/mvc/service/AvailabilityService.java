package br.com.eponciano.ms.booking.mvc.service;

import java.time.LocalDate;
import java.util.List;

import br.com.eponciano.ms.booking.commons.model.dto.RoomSelectionDTO;


public interface AvailabilityService {

    Integer getMinAvailableRooms(Long roomId, LocalDate checkinDate, LocalDate checkoutDate);

    void updateAvailabilities(long hotelId, LocalDate checkinDate, LocalDate checkoutDate, List<RoomSelectionDTO> roomSelections);

}
