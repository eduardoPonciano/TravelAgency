package br.com.eduardo.ponciano.travel.hotel.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.eduardo.ponciano.travel.commons.model.dto.HotelDTO;
import br.com.eduardo.ponciano.travel.commons.model.dto.HotelRegistrationDTO;
import br.com.eduardo.ponciano.travel.hotel.model.Hotel;
import br.com.eduardo.ponciano.travel.hotel.service.HotelService;

@RestController
@RequestMapping
public class HotelController {

    @Autowired
    private HotelService hotelService;

    @PostMapping("/hotel")
    public Hotel saveHotel(@RequestBody HotelRegistrationDTO hotelRegistrationDTO) {
    	return hotelService.saveHotel(hotelRegistrationDTO);
    }
    
    @GetMapping("/hotel/dto/name/{name}")
    public HotelDTO findHotelDtoByName(@PathVariable String name) {
    	return hotelService.findHotelDtoByName(name);
    }

    @GetMapping("/hotel/dto/id/{id}")
    public HotelDTO findHotelDtoById(@PathVariable Long id) {
    	return hotelService.findHotelDtoById(id);
    }

    @GetMapping("/hotel/{id}")
    public Optional<Hotel> findHotelById(@PathVariable Long id) {
    	return hotelService.findHotelById(id);
    }


    @PutMapping("/hotel")
    public HotelDTO updateHotel(@RequestBody HotelDTO hotelDTO) {
    	return hotelService.updateHotel(hotelDTO);
    }




    @GetMapping("/hotels")
    public List<HotelDTO> findAllHotels() {
    	return hotelService.findAllHotels();
    }
    
    @DeleteMapping("/hotel/{id}")
    public void deleteHotelById(@PathVariable Long id) {
    	hotelService.deleteHotelById(id);;
    }
    
    
    @GetMapping("/hotel/dto/")
    public HotelDTO mapHotelToHotelDto(@RequestBody Hotel hotel) {
    	return hotelService.mapHotelToHotelDto(hotel);
    }
}
