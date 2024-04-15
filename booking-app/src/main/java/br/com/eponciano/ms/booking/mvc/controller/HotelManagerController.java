package br.com.eponciano.ms.booking.mvc.controller;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.eponciano.ms.booking.commons.model.dto.HotelDTO;
import br.com.eponciano.ms.booking.commons.model.dto.HotelRegistrationDTO;
import br.com.eponciano.ms.booking.commons.model.dto.RoomDTO;
import br.com.eponciano.ms.booking.mvc.client.HotelFeignClient;
import br.com.eponciano.ms.booking.mvc.client.UserFeignClient;
import br.com.eponciano.ms.booking.mvc.exception.HotelAlreadyExistsException;
import br.com.eponciano.ms.booking.mvc.model.dto.BookingDTO;
import br.com.eponciano.ms.booking.mvc.service.BookingService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/manager")
@RequiredArgsConstructor
@Slf4j
public class HotelManagerController {

    @Autowired
    private HotelFeignClient hotelService;
	@Autowired
	private final UserFeignClient userService;
    private final BookingService bookingService;

    @GetMapping("/dashboard")
    public String dashboard() {
        return "hotelmanager/dashboard";
    }

    @GetMapping("/hotels/add")
    public String showAddHotelForm(Model model) {
        HotelRegistrationDTO hotelRegistrationDTO = new HotelRegistrationDTO();

        RoomDTO singleRoom = new RoomDTO(null, null, null, 0, BigDecimal.ZERO);
        RoomDTO doubleRoom = new RoomDTO(null, null, null, 0, BigDecimal.ZERO);
        hotelRegistrationDTO.getRoomDTOs().add(singleRoom);
        hotelRegistrationDTO.getRoomDTOs().add(doubleRoom);

        model.addAttribute("hotel", hotelRegistrationDTO);
        return "hotelmanager/hotels-add";
    }

    @PostMapping("/hotels/add")
    public String addHotel(@Valid @ModelAttribute("hotel") HotelRegistrationDTO hotelRegistrationDTO, BindingResult result, RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            log.warn("Erro ao cadastrar um Hotel- erro de validação: {}", result.getAllErrors());
            return "hotelmanager/hotels-add";
        }
        try {
            hotelService.saveHotel(hotelRegistrationDTO);
            redirectAttributes.addFlashAttribute("message", "Hotel (" + hotelRegistrationDTO.getName() + ") cadastrado com sucesso");
            return "redirect:/manager/hotels";
        } catch (HotelAlreadyExistsException e) {
            result.rejectValue("name", "hotel.exists", e.getMessage());
            return "hotelmanager/hotels-add";
        }
    }

    @GetMapping("/hotels")
    public String listHotels(Model model) {
        Long managerId = getCurrentManagerId();
        List<HotelDTO> hotelList = hotelService.findAllHotels(managerId);
        model.addAttribute("hotels", hotelList);
        return "hotelmanager/hotels";
    }

    @GetMapping("/hotels/edit/{id}")
    public String showEditHotelForm(@PathVariable Long id, Model model) {
        Long managerId = getCurrentManagerId();
        HotelDTO hotelDTO = hotelService.findByParameters(id,null, managerId);
        model.addAttribute("hotel", hotelDTO);
        return "hotelmanager/hotels-edit";
    }

    @PostMapping("/hotels/edit/{id}")
    public String editHotel(@PathVariable Long id, @Valid @ModelAttribute("hotel") HotelDTO hotelDTO, BindingResult result, RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            return "hotelmanager/hotels-edit";
        }
        try {
            Long managerId = getCurrentManagerId();
            hotelDTO.setId(id);
            hotelService.updateHotel(hotelDTO);
            redirectAttributes.addFlashAttribute("message", "Hotel (ID: " + id + ") atualizado com sucesso");
            return "redirect:/manager/hotels";

        } catch (HotelAlreadyExistsException e) {
            result.rejectValue("name", "hotel.exists", e.getMessage());
            return "hotelmanager/hotels-edit";
        } catch (EntityNotFoundException e) {
            result.rejectValue("id", "hotel.notfound", e.getMessage());
            return "hotelmanager/hotels-edit";
        }
    }

    @PostMapping("/hotels/delete/{id}")
    public String deleteHotel(@PathVariable Long id) {
//        Long managerId = getCurrentManagerId();
        hotelService.deleteHotelById(id);
        return "redirect:/manager/hotels";
    }

    @GetMapping("/bookings")
    public String listBookings(Model model, RedirectAttributes redirectAttributes) {
        try {
            Long managerId = getCurrentManagerId();
            List<BookingDTO> bookingDTOs = bookingService.findBookingsByManagerId(managerId);
            model.addAttribute("bookings", bookingDTOs);

            return "hotelmanager/bookings";
        } catch (EntityNotFoundException e) {
            log.error("Reserva não encontrada", e);
            redirectAttributes.addFlashAttribute("errorMessage", "Reserva não encontrada.");
            return "redirect:/manager/dashboard";
        } catch (Exception e) {
            log.error("Erro ao listar reservas", e);
            redirectAttributes.addFlashAttribute("errorMessage", "Erro Inesperado.");
            return "redirect:/manager/dashboard";
        }
    }

    @GetMapping("/bookings/{id}")
    public String viewBookingDetails(@PathVariable Long id, Model model, RedirectAttributes redirectAttributes) {
        try {
            Long managerId = getCurrentManagerId();
            BookingDTO bookingDTO = bookingService.findBookingByIdAndManagerId(id, managerId);
            model.addAttribute("bookingDTO", bookingDTO);

            LocalDate checkinDate = bookingDTO.getCheckinDate();
            LocalDate checkoutDate = bookingDTO.getCheckoutDate();
            long durationDays = ChronoUnit.DAYS.between(checkinDate, checkoutDate);
            model.addAttribute("days", durationDays);

            return "hotelmanager/bookings-details";
        } catch (EntityNotFoundException e) {
            log.error("Reserva não encontrada", e);
            redirectAttributes.addFlashAttribute("errorMessage", "Reserva não encontrada.");
            return "redirect:/manager/dashboard";
        } catch (Exception e) {
            log.error("Erro ao carredar dados da reserva", e);
            redirectAttributes.addFlashAttribute("errorMessage", "Erro inesperado.");
            return "redirect:/manager/dashboard";
        }
    }

    private Long getCurrentManagerId() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        return userService.findUserByUsername(username).getHotelManager().getId();
    }
}
