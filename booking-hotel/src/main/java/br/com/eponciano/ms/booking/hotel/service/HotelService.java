package br.com.eponciano.ms.booking.hotel.service;

import java.util.List;

import br.com.eponciano.ms.booking.commons.model.dto.HotelDTO;
import br.com.eponciano.ms.booking.commons.model.dto.HotelRegistrationDTO;
import br.com.eponciano.ms.booking.hotel.model.Hotel;

public interface HotelService {

    Hotel saveHotel(HotelRegistrationDTO hotelRegistrationDTO);

    HotelDTO findByParameters(Long id,String name, Long managerId);

    List<HotelDTO> findAllHotels(Long managerId);

    HotelDTO updateHotel(HotelDTO hotelDTO);

    void deleteHotelById(Long id);
    

//  HotelDTO findHotelDtoByName(String name);
//
//  HotelDTO findHotelDtoById(Long id);
//
//  Optional<Hotel> findHotelById(Long id);

}

