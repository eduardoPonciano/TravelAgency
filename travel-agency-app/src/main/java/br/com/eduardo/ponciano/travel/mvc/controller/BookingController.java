package br.com.eduardo.ponciano.travel.mvc.controller;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.eduardo.ponciano.travel.commons.model.dto.AiplaneDTO;
import br.com.eduardo.ponciano.travel.commons.model.dto.BookingInitiationDTO;
import br.com.eduardo.ponciano.travel.commons.model.dto.CarDTO;
import br.com.eduardo.ponciano.travel.commons.model.dto.ProcesssBookingDTO;
import br.com.eduardo.ponciano.travel.mvc.consumer.UserServiceConsumer;
import br.com.eduardo.ponciano.travel.mvc.jms.ProcessBookingPublish;
import br.com.eduardo.ponciano.travel.mvc.mapper.ProcesssBookingMapper;
import br.com.eduardo.ponciano.travel.mvc.model.dto.BookingDTO;
import br.com.eduardo.ponciano.travel.mvc.model.dto.HotelDTO;
import br.com.eduardo.ponciano.travel.mvc.model.dto.PaymentCardDTO;
import br.com.eduardo.ponciano.travel.mvc.model.dto.UserDTO;
import br.com.eduardo.ponciano.travel.mvc.service.BookingService;
import br.com.eduardo.ponciano.travel.mvc.service.HotelService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/booking")
@RequiredArgsConstructor
@Slf4j
public class BookingController {

    @Autowired
    private ProcessBookingPublish processPublish;
    private final HotelService hotelService;
	@Autowired
	private final UserServiceConsumer userService;
    private final BookingService bookingService;

    @PostMapping("/initiate")
    public String initiatePostBooking(@ModelAttribute("carDTO") CarDTO carDTO, Model model,HttpSession session) {

        return "redirect:/booking/initiate";
    }

    @GetMapping("/initiate")
    public String initiateBooking(@ModelAttribute("carDTO") CarDTO carDTO, Model model,HttpSession session) {

    	if(carDTO.getMarca()==null)
    		carDTO = (CarDTO) session.getAttribute("carFormData");
    	AiplaneDTO airplaneDTO = (AiplaneDTO)session.getAttribute("airplaneDTO");    	
    	BookingInitiationDTO bookingInitiationDTO = (BookingInitiationDTO)session.getAttribute("bookingFormData");
        session.setAttribute("carDTO", carDTO);
        session.setAttribute("bookingInitiationDTO", bookingInitiationDTO);
        log.debug("BookingInitiationDTO adcionado a sessão: {}", bookingInitiationDTO);
        return "redirect:/booking/payment";
    }

    @GetMapping("/payment")
    public String showPaymentPage(Model model, RedirectAttributes redirectAttributes, HttpSession session) {
        BookingInitiationDTO bookingInitiationDTO = (BookingInitiationDTO) session.getAttribute("bookingInitiationDTO");
        log.debug("BookingInitiationDTO recuperado da sessão: {}", bookingInitiationDTO);

        if (bookingInitiationDTO == null) {
            redirectAttributes.addFlashAttribute("errorMessage", "Sessão expirada.");
            return "redirect:/search";
        }

        HotelDTO hotelDTO = hotelService.findHotelDtoById(bookingInitiationDTO.getHotelId());

        model.addAttribute("airplaneDTO",(AiplaneDTO) session.getAttribute("airplaneDTO"));
        model.addAttribute("carDTO", (CarDTO) session.getAttribute("carDTO"));
        model.addAttribute("bookingInitiationDTO", bookingInitiationDTO);
        model.addAttribute("hotelDTO", hotelDTO);
        model.addAttribute("paymentCardDTO", new PaymentCardDTO());

        return "booking/payment";
    }

    @PostMapping("/payment")
    public String confirmBooking(@Valid @ModelAttribute("paymentCardDTO") PaymentCardDTO paymentDTO, BindingResult result, Model model, HttpSession session, RedirectAttributes redirectAttributes) {
        BookingInitiationDTO bookingInitiationDTO = (BookingInitiationDTO) session.getAttribute("bookingInitiationDTO");
        log.debug("BookingInitiationDTO recuperado da sessão: {}", bookingInitiationDTO);
        if (bookingInitiationDTO == null) {
            redirectAttributes.addFlashAttribute("errorMessage", "Sessão expirada.");
            return "redirect:/search";
        }

        if (result.hasErrors()) {
            log.warn("Erro de validação ao concluir reserva");
            HotelDTO hotelDTO = hotelService.findHotelDtoById(bookingInitiationDTO.getHotelId());

            model.addAttribute("airplaneDTO",(AiplaneDTO) session.getAttribute("airplaneDTO"));
            model.addAttribute("carDTO", (CarDTO) session.getAttribute("carDTO"));
            model.addAttribute("bookingInitiationDTO", bookingInitiationDTO);
            model.addAttribute("hotelDTO", hotelDTO);
            model.addAttribute("paymentCardDTO", paymentDTO);
            return "booking/payment";
        }

        try {
            Long userId = getLoggedInUserId();
            
            
            BookingDTO bookingDTO = bookingService.confirmBooking(bookingInitiationDTO, userId);
            ProcesssBookingDTO initiationToProcess = ProcesssBookingMapper.initiationToProcess(bookingDTO);
            initiationToProcess.getHotel().setDurationDays(bookingInitiationDTO.getDurationDays());
            initiationToProcess.getHotel().setRoomSelections(bookingInitiationDTO.getRoomSelections());
            
			processPublish.process(initiationToProcess);
            redirectAttributes.addFlashAttribute("bookingDTO", bookingDTO);

            return "redirect:/booking/confirmation";
        } catch (Exception e) {
            log.error("Erro ao concluir a reserva", e);
            redirectAttributes.addFlashAttribute("errorMessage", "Ocorreu um erro inesperado.");
            return "redirect:/booking/payment";
        }
    }

    @GetMapping("/confirmation")
    public String showConfirmationPage(Model model, RedirectAttributes redirectAttributes) {
        BookingDTO bookingDTO = (BookingDTO) model.asMap().get("bookingDTO");

        if (bookingDTO == null) {
            redirectAttributes.addFlashAttribute("errorMessage", "Sessão expirada ou o processamento de reserva não condluida adequadamente.");
            return "redirect:/search";
        }
        model.asMap().get("bookingDTO");

        LocalDate checkinDate = bookingDTO.getCheckinDate();
        LocalDate checkoutDate = bookingDTO.getCheckoutDate();
        long durationDays = ChronoUnit.DAYS.between(checkinDate, checkoutDate);

        model.addAttribute("days", durationDays);
        model.addAttribute("bookingDTO", bookingDTO);
        


        return "booking/confirmation";
    }

    private Long getLoggedInUserId() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        UserDTO userDTO = userService.findUserDTOByUsername(username);
        log.info("Busca de Usuario Logado ID: {}", userDTO.getId());
        return userDTO.getId();
    }

}