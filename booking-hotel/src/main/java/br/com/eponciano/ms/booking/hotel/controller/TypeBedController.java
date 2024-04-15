package br.com.eponciano.ms.booking.hotel.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.eponciano.ms.booking.hotel.model.TypeBedDTO;
import br.com.eponciano.ms.booking.hotel.service.TypeBedService;

@RestController
@RequestMapping
public class TypeBedController {

    @Autowired
    private TypeBedService roomService;

    @PostMapping("/type-bed")
    public TypeBedDTO saveHotel(@RequestBody TypeBedDTO typeBed) {
    	return roomService.saveTypeBed(typeBed);
    }

    @GetMapping("/type-beds")
    public List<TypeBedDTO> list() {
    	return roomService.findAll();
    }

}
