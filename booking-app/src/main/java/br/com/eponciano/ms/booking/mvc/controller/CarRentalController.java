package br.com.eponciano.ms.booking.mvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import br.com.eduardo.ponciano.travel.commons.model.dto.AiplaneDTO;
import br.com.eduardo.ponciano.travel.commons.model.dto.CarDTO;
import jakarta.servlet.http.HttpSession;

@Controller
public class CarRentalController {


    @PostMapping("/car-rental")
    public String showCarRental(@ModelAttribute("aiplaneDTO") AiplaneDTO airplaneDTO, Model model,HttpSession session) {
    	AiplaneDTO airplaneSession = (AiplaneDTO) session.getAttribute("airplaneDTO");
    	airplaneSession.setAssento(airplaneDTO.getAssento());
    	session.setAttribute("airplaneDTO",airplaneSession);
    	
    	CarDTO car = new CarDTO();
    	model.addAttribute("carDTO",car);
        return "car-rental/cars";
    }

    @PostMapping("/store-car-data")
    public void storeBookingData(@ModelAttribute CarDTO carDTO, HttpSession session) {
        session.setAttribute("carFormData", carDTO);
    }
    

}