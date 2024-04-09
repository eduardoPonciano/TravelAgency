package br.com.eduardo.ponciano.travel.mvc.service;

import java.time.LocalDate;
import java.util.List;

import br.com.eduardo.ponciano.travel.mvc.model.Hotel;
import br.com.eduardo.ponciano.travel.mvc.model.dto.HotelAvailabilityDTO;

public interface HotelSearchService {

    List<HotelAvailabilityDTO> findAvailableHotelsByCityAndDate(String city, LocalDate checkinDate, LocalDate checkoutDate);

    HotelAvailabilityDTO findAvailableHotelById(Long hotelId, LocalDate checkinDate, LocalDate checkoutDate);

    HotelAvailabilityDTO mapHotelToHotelAvailabilityDto(Hotel hotel, LocalDate checkinDate, LocalDate checkoutDate);
}
