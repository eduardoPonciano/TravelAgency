package br.com.eponciano.ms.booking.mvc.client;
import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import br.com.eduardo.ponciano.travel.commons.model.dto.HotelDTO;
import br.com.eduardo.ponciano.travel.commons.model.dto.HotelRegistrationDTO;
import br.com.eponciano.ms.booking.mvc.model.Hotel;


@FeignClient(name = "hotel-service", url = "${hotel.service.url}")
public interface HotelFeignClient {

    @PostMapping("/hotel")
    public Hotel saveHotel(@RequestBody HotelRegistrationDTO hotelRegistrationDTO);

    @GetMapping("/hotel")
    public HotelDTO findByParameters(
    		@RequestParam(value = "id", required = false) Long id,
    		@RequestParam(value = "name", required = false) String name,
			@RequestParam(value = "managerId", required = false) Long managerId);


    @PutMapping("/hotel")
    public HotelDTO updateHotel(@RequestBody HotelDTO hotelDTO);



    @GetMapping("/hotels")
    public List<HotelDTO> findAllHotels(
			@RequestParam(value = "managerId", required = false) Long managerId);
    
    @DeleteMapping("/hotel/{id}")
    public void deleteHotelById(@PathVariable Long id);

}