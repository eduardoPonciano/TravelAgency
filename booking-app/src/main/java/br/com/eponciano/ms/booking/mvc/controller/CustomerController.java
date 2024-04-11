package br.com.eponciano.ms.booking.mvc.controller;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.eponciano.ms.booking.mvc.consumer.UserServiceConsumer;
import br.com.eponciano.ms.booking.mvc.model.dto.BookingDTO;
import br.com.eponciano.ms.booking.mvc.service.BookingService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/customer")
@RequiredArgsConstructor
@Slf4j
public class CustomerController {

	@Autowired
	private final UserServiceConsumer userService;
    private final BookingService bookingService;

    @GetMapping("/dashboard")
    public String dashboard() {
        return "customer/dashboard";
    }

    @GetMapping("/bookings")
    public String listBookings(Model model, RedirectAttributes redirectAttributes) {
        try {
            Long customerId = getCurrentCustomerId();
            List<BookingDTO> bookingDTOs = bookingService.findBookingsByCustomerId(customerId);
            model.addAttribute("bookings", bookingDTOs);
            return "customer/bookings";
        } catch (EntityNotFoundException e) {
            log.error("Cliente não encontrado", e);
            redirectAttributes.addFlashAttribute("errorMessage", "Cliente não encontrado. Refaça o login.");
            return "redirect:/login";
        } catch (Exception e) {
            log.error("Erro ao listar as reservas", e);
            redirectAttributes.addFlashAttribute("errorMessage", "Erro inesperado.");
            return "redirect:/";
        }
    }

    @GetMapping("/bookings/{id}")
    public String viewBookingDetails(@PathVariable Long id, Model model, RedirectAttributes redirectAttributes) {
        try {
            Long customerId = getCurrentCustomerId();
            BookingDTO bookingDTO = bookingService.findBookingByIdAndCustomerId(id, customerId);
            model.addAttribute("bookingDTO", bookingDTO);

            LocalDate checkinDate = bookingDTO.getCheckinDate();
            LocalDate checkoutDate = bookingDTO.getCheckoutDate();
            long durationDays = ChronoUnit.DAYS.between(checkinDate, checkoutDate);
            model.addAttribute("days", durationDays);

            return "customer/bookings-details";
        } catch (EntityNotFoundException e) {
            log.error("Reserva não encontrada", e);
            redirectAttributes.addFlashAttribute("errorMessage", "Reserva não encontrada.");
            return "redirect:/customer/bookings";
        } catch (Exception e) {
            log.error("An error occurred while displaying booking details", e);
            redirectAttributes.addFlashAttribute("errorMessage", "Erro inesperado.");
            return "redirect:/";
        }
    }

    private Long getCurrentCustomerId() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        return userService.findUserByUsername(username).getCustomer().getId();
    }

}