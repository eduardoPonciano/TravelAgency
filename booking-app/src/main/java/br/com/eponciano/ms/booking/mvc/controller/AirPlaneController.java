package br.com.eponciano.ms.booking.mvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.eponciano.ms.booking.commons.model.dto.AiplaneDTO;
import br.com.eponciano.ms.booking.mvc.model.dto.HotelSearchDTO;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/airplane")
public class AirPlaneController {

    @PostMapping("/airlines")
    public String showAirlines(@ModelAttribute("hotelSearchDTO") HotelSearchDTO hotelSearchDTO, Model model, HttpSession session) {

    	AiplaneDTO airplaneDTO = new AiplaneDTO();
        model.addAttribute("airplaneDTO", airplaneDTO);
        return "airplane/airlines";
    }
    
    @PostMapping("/assentos")
    public String showAssentos(@ModelAttribute("aiplaneDTO") AiplaneDTO airplaneDTO, Model model, HttpSession session) {

    	model.addAttribute("airplaneDTO", airplaneDTO);
        session.setAttribute("airplaneDTO", airplaneDTO);
    	//guardar compahia area na sessão
        return "airplane/assentos";
    }

    

}