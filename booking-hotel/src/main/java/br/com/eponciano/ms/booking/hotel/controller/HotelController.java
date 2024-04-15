package br.com.eponciano.ms.booking.hotel.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.eponciano.ms.booking.commons.model.dto.HotelDTO;
import br.com.eponciano.ms.booking.commons.model.dto.HotelRegistrationDTO;
import br.com.eponciano.ms.booking.hotel.model.Hotel;
import br.com.eponciano.ms.booking.hotel.service.HotelService;

@RestController
@RequestMapping
public class HotelController {

    @Autowired
    private HotelService hotelService;

    @PostMapping("/hotel")
    public Hotel saveHotel(@RequestBody HotelRegistrationDTO hotelRegistrationDTO) {
    	return hotelService.saveHotel(hotelRegistrationDTO);
    }
    
    @GetMapping("/hotel")
    public HotelDTO findByParameters(
    		@RequestParam(value = "id", required = false) Long id,
    		@RequestParam(value = "name", required = false) String name,
			@RequestParam(value = "managerId", required = false) Long managerId
			) {
				return hotelService.findByParameters(id, name, managerId);

    }

    @PutMapping("/hotel")
    public HotelDTO updateHotel(@RequestBody HotelDTO hotelDTO) {
    	return hotelService.updateHotel(hotelDTO);
    }




    @GetMapping("/hotels")
    public List<HotelDTO> findAllHotels(@RequestParam(value = "managerId", required = false) Long managerId) {
    	return hotelService.findAllHotels(managerId);
    }
    
    @DeleteMapping("/hotel/{id}")
    public void deleteHotelById(@PathVariable Long id) {
    	hotelService.deleteHotelById(id);;
    }
    
}
