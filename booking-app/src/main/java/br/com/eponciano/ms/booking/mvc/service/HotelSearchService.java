package br.com.eponciano.ms.booking.mvc.service;

import java.time.LocalDate;
import java.util.List;

import br.com.eponciano.ms.booking.mvc.model.Hotel;
import br.com.eponciano.ms.booking.mvc.model.dto.HotelAvailabilityDTO;

public interface HotelSearchService {

    List<HotelAvailabilityDTO> findAvailableHotelsByCityAndDate(String city, LocalDate checkinDate, LocalDate checkoutDate);

    HotelAvailabilityDTO findAvailableHotelById(Long hotelId, LocalDate checkinDate, LocalDate checkoutDate);

    HotelAvailabilityDTO mapHotelToHotelAvailabilityDto(Hotel hotel, LocalDate checkinDate, LocalDate checkoutDate);
}
