package br.com.eduardo.ponciano.travel.mvc.controller;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.eduardo.ponciano.travel.commons.model.dto.BookingInitiationDTO;
import br.com.eduardo.ponciano.travel.mvc.model.dto.HotelAvailabilityDTO;
import br.com.eduardo.ponciano.travel.mvc.model.dto.HotelSearchDTO;
import br.com.eduardo.ponciano.travel.mvc.service.HotelSearchService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequiredArgsConstructor
@Slf4j
public class HotelSearchController {

    private final HotelSearchService hotelSearchService;

    @GetMapping("/search")
    public String showSearchForm(@ModelAttribute("hotelSearchDTO") HotelSearchDTO hotelSearchDTO) {
        return "hotelsearch/search";
    }

    @PostMapping("/store-booking-data")
    public void storeBookingData(@ModelAttribute BookingInitiationDTO bookingInitiationDTO, HttpSession session) {
        session.setAttribute("bookingFormData", bookingInitiationDTO);
    }


    @PostMapping("/search")
    public String findAvailableHotelsByCityAndDate(@Valid @ModelAttribute("hotelSearchDTO") HotelSearchDTO hotelSearchDTO, BindingResult result) {
        if (result.hasErrors()) {
            return "hotelsearch/search";
        }
        try {
            validateCheckinAndCheckoutDates(hotelSearchDTO.getCheckinDate(), hotelSearchDTO.getCheckoutDate());
        } catch (IllegalArgumentException e) {
            result.rejectValue("checkoutDate", null, e.getMessage());
            return "hotelsearch/search";
        }

        // Redirect to a new GET endpoint with parameters for data fetching. Allows page refreshing
        return String.format("redirect:/search-results?city=%s&checkinDate=%s&checkoutDate=%s", hotelSearchDTO.getCity(), hotelSearchDTO.getCheckinDate(), hotelSearchDTO.getCheckoutDate());
    }

    @GetMapping("/search-results")
    public String showSearchResults(@RequestParam String city, @RequestParam String checkinDate, @RequestParam String checkoutDate, Model model, RedirectAttributes redirectAttributes) {
        try {
            LocalDate parsedCheckinDate = LocalDate.parse(checkinDate);
            LocalDate parsedCheckoutDate = LocalDate.parse(checkoutDate);

            validateCheckinAndCheckoutDates(parsedCheckinDate, parsedCheckoutDate);

            log.info("Buscando hoteis para a cidade {} com datas disponiveis entre {} e {}", city, checkinDate, checkoutDate);
            List<HotelAvailabilityDTO> hotels = hotelSearchService.findAvailableHotelsByCityAndDate(city, parsedCheckinDate, parsedCheckoutDate);

            if (hotels.isEmpty()) {
                model.addAttribute("noHotelsFound", true);
            }

            long durationDays = ChronoUnit.DAYS.between(parsedCheckinDate, parsedCheckoutDate);

            model.addAttribute("hotels", hotels);
            model.addAttribute("city", city);
            model.addAttribute("days", durationDays);
            model.addAttribute("checkinDate", checkinDate);
            model.addAttribute("checkoutDate", checkoutDate);

        } catch (DateTimeParseException e) {
            log.error("Formato de data invalida", e);
            redirectAttributes.addFlashAttribute("errorMessage", "Formato de data invalida.");
            return "redirect:/search";
        } catch (IllegalArgumentException e) {
            log.error("Argumentos invalidos", e);
            redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());
            return "redirect:/search";
        } catch (Exception e) {
            log.error("Erro ao procurar o hotel", e);
            redirectAttributes.addFlashAttribute("errorMessage", "Erro inesperado.");
            return "redirect:/search";
        }

        return "hotelsearch/search-results";
    }

    @GetMapping("/hotel-details/{id}")
    public String showHotelDetails(@PathVariable Long id, @RequestParam String checkinDate, @RequestParam String checkoutDate, Model model, RedirectAttributes redirectAttributes, HttpSession session) {
        try {
            LocalDate parsedCheckinDate = LocalDate.parse(checkinDate);
            LocalDate parsedCheckoutDate = LocalDate.parse(checkoutDate);

            validateCheckinAndCheckoutDates(parsedCheckinDate, parsedCheckoutDate);

            HotelAvailabilityDTO hotelAvailabilityDTO = hotelSearchService.findAvailableHotelById(id, parsedCheckinDate, parsedCheckoutDate);

            long durationDays = ChronoUnit.DAYS.between(parsedCheckinDate, parsedCheckoutDate);

            model.addAttribute("hotel", hotelAvailabilityDTO);
            model.addAttribute("durationDays", durationDays);
            model.addAttribute("checkinDate", checkinDate);
            model.addAttribute("checkoutDate", checkoutDate);
            
            session.setAttribute("model", model);
            return "hotelsearch/hotel-details";


        } catch (DateTimeParseException e) {
            log.error("Formato de data invalida", e);
            redirectAttributes.addFlashAttribute("errorMessage", "Formato de data invalida.");
            return "redirect:/search";
        } catch (IllegalArgumentException e) {
            log.error("Argumentos Invalidos", e);
            redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());
            return "redirect:/search";
        } catch (EntityNotFoundException e) {
            log.error("Hotel não encontrado {}", id);
            redirectAttributes.addFlashAttribute("errorMessage", "hotel não encotrado.");
            return "redirect:/search";
        } catch (Exception e) {
            log.error("Erro ao buscar hotel", e);
            redirectAttributes.addFlashAttribute("errorMessage", "Erro inesperado.");
            return "redirect:/search";
        }
    }

    private void validateCheckinAndCheckoutDates(LocalDate checkinDate, LocalDate checkoutDate) {
        if (checkinDate.isBefore(LocalDate.now())) {
            throw new IllegalArgumentException("data de inicio deve estar numa data futura");
        }
        if (checkoutDate.isBefore(checkinDate.plusDays(1))) {
            throw new IllegalArgumentException("data de saida deve ser após a data de inicio");
        }
    }

    private void parseAndValidateBookingDates(String checkinDate, String checkoutDate) {
        LocalDate parsedCheckinDate = LocalDate.parse(checkinDate);
        LocalDate parsedCheckoutDate = LocalDate.parse(checkoutDate);
        validateCheckinAndCheckoutDates(parsedCheckinDate, parsedCheckoutDate);
    }

}