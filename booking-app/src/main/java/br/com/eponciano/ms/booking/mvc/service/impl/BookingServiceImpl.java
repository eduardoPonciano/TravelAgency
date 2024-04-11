package br.com.eponciano.ms.booking.mvc.service.impl;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import br.com.eduardo.ponciano.travel.commons.model.dto.BookingInitiationDTO;
import br.com.eduardo.ponciano.travel.commons.model.dto.HotelDTO;
import br.com.eponciano.ms.booking.mvc.client.HotelFeignClient;
import br.com.eponciano.ms.booking.mvc.model.BookedRoom;
import br.com.eponciano.ms.booking.mvc.model.Booking;
import br.com.eponciano.ms.booking.mvc.model.Customer;
import br.com.eponciano.ms.booking.mvc.model.Hotel;
import br.com.eponciano.ms.booking.mvc.model.Payment;
import br.com.eponciano.ms.booking.mvc.model.User;
import br.com.eponciano.ms.booking.mvc.model.dto.AddressDTO;
import br.com.eponciano.ms.booking.mvc.model.dto.BookingDTO;
import br.com.eponciano.ms.booking.mvc.model.dto.RoomSelectionDTO;
import br.com.eponciano.ms.booking.mvc.repository.BookingRepository;
import br.com.eponciano.ms.booking.mvc.service.AvailabilityService;
import br.com.eponciano.ms.booking.mvc.service.BookingService;
import br.com.eponciano.ms.booking.mvc.service.CustomerService;
import br.com.eponciano.ms.booking.mvc.service.PaymentService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BookingServiceImpl implements BookingService {

    private final BookingRepository bookingRepository;
    private final AvailabilityService availabilityService;
    private final PaymentService paymentService;
    private final CustomerService customerService;
    private final HotelFeignClient hotelService;


    @Override
    @Transactional
    public Booking saveBooking(BookingInitiationDTO bookingInitiationDTO, Long userId) {
        validateBookingDates(bookingInitiationDTO.getCheckinDate(), bookingInitiationDTO.getCheckoutDate());

        Customer customer = customerService.findByUserId(userId)
                .orElseThrow(() -> new EntityNotFoundException("Customer not found with user ID: " + userId));

        HotelDTO hotel = hotelService.findByParameters(bookingInitiationDTO.getHotelId(),null,null);
                if(hotel==null) {
                	new EntityNotFoundException("Hotel not found with ID: " + bookingInitiationDTO.getHotelId());
                }

        Booking booking = mapBookingInitDtoToBookingModel(bookingInitiationDTO, customer, hotel);

        return bookingRepository.save(booking);
    }

    @Override
    @Transactional
    public BookingDTO confirmBooking(BookingInitiationDTO bookingInitiationDTO, Long customerId) {
        Booking savedBooking = this.saveBooking(bookingInitiationDTO, customerId);
        Payment savedPayment = paymentService.savePayment(bookingInitiationDTO, savedBooking);
        savedBooking.setPayment(savedPayment);
        bookingRepository.save(savedBooking);
        availabilityService.updateAvailabilities(bookingInitiationDTO.getHotelId(), bookingInitiationDTO.getCheckinDate(),
                bookingInitiationDTO.getCheckoutDate(), bookingInitiationDTO.getRoomSelections());
        return mapBookingModelToBookingDto(savedBooking);
    }

    @Override
    public List<BookingDTO> findAllBookings() {
        List<Booking> bookings = bookingRepository.findAll();
        return bookings.stream()
                .map(this::mapBookingModelToBookingDto)
                .collect(Collectors.toList());
    }

    @Override
    public BookingDTO findBookingById(Long bookingId) {
        Booking booking = bookingRepository.findById(bookingId)
                .orElseThrow(() -> new EntityNotFoundException("Booking not found with ID: " + bookingId));
        return mapBookingModelToBookingDto(booking);
    }

    @Override
    public List<BookingDTO> findBookingsByCustomerId(Long customerId) {
        List<Booking> bookingDTOs = bookingRepository.findBookingsByCustomerId(customerId);
        return bookingDTOs.stream()
                .map(this::mapBookingModelToBookingDto)
                .sorted(Comparator.comparing(BookingDTO::getCheckinDate))
                .collect(Collectors.toList());
    }

    @Override
    public BookingDTO findBookingByIdAndCustomerId(Long bookingId, Long customerId) {
        Booking booking = bookingRepository.findBookingByIdAndCustomerId(bookingId, customerId)
                .orElseThrow(() -> new EntityNotFoundException("Booking not found with ID: " + bookingId));
        return mapBookingModelToBookingDto(booking);
    }

    @Override
    public List<BookingDTO> findBookingsByManagerId(Long managerId) {
        List<HotelDTO> hotels = hotelService.findAllHotels(managerId);
        return hotels.stream()
                .flatMap(hotel -> bookingRepository.findBookingsByHotelId(hotel.getId()).stream())
                .map(this::mapBookingModelToBookingDto)
                .collect(Collectors.toList());
    }

    @Override
    public BookingDTO findBookingByIdAndManagerId(Long bookingId, Long managerId) {
        Booking booking = bookingRepository.findBookingByIdAndHotel_HotelManagerId(bookingId, managerId)
                .orElseThrow(() -> new EntityNotFoundException("Booking not found with ID: " + bookingId + " and manager ID: " + managerId));
        return mapBookingModelToBookingDto(booking);
    }

    private void validateBookingDates(LocalDate checkinDate, LocalDate checkoutDate) {
        if (checkinDate.isBefore(LocalDate.now())) {
            throw new IllegalArgumentException("Check-in date cannot be in the past");
        }
        if (checkoutDate.isBefore(checkinDate.plusDays(1))) {
            throw new IllegalArgumentException("Check-out date must be after check-in date");
        }
    }

    @Override
    public BookingDTO mapBookingModelToBookingDto(Booking booking) {
        AddressDTO addressDto = AddressDTO.builder()
                .addressLine(booking.getHotel().getAddress().getAddressLine())
                .city(booking.getHotel().getAddress().getCity())
                .country(booking.getHotel().getAddress().getCountry())
                .build();

        List<RoomSelectionDTO> roomSelections = booking.getBookedRooms().stream()
                .map(room -> RoomSelectionDTO.builder()
                        .roomType(room.getRoomType())
                        .count(room.getCount())
                        .build())
                .collect(Collectors.toList());

        User customerUser = booking.getCustomer().getUser();

        return BookingDTO.builder()
                .id(booking.getId())
                .confirmationNumber(booking.getConfirmationNumber())
                .bookingDate(booking.getBookingDate())
                .customerId(booking.getCustomer().getId())
                .hotelId(booking.getHotel().getId())
                .checkinDate(booking.getCheckinDate())
                .checkoutDate(booking.getCheckoutDate())
                .roomSelections(roomSelections)
                .totalPrice(booking.getPayment().getTotalPrice())
                .hotelName(booking.getHotel().getName())
                .hotelAddress(addressDto)
                .customerName(customerUser.getName() + " " + customerUser.getLastName())
                .customerEmail(customerUser.getUsername())
                .paymentStatus(booking.getPayment().getPaymentStatus())
                .paymentMethod(booking.getPayment().getPaymentMethod())
                .build();
    }

    private Booking mapBookingInitDtoToBookingModel(BookingInitiationDTO bookingInitiationDTO, Customer customer, HotelDTO hotel) {
        Booking booking = Booking.builder()
                .customer(customer)
                .hotel(Hotel.builder().id(hotel.getId()).build())
                .checkinDate(bookingInitiationDTO.getCheckinDate())
                .checkoutDate(bookingInitiationDTO.getCheckoutDate())
                .build();

        for (br.com.eduardo.ponciano.travel.commons.model.dto.RoomSelectionDTO roomSelection : bookingInitiationDTO.getRoomSelections()) {
            if (roomSelection.getCount() > 0) {
                BookedRoom bookedRoom = BookedRoom.builder()
                        .booking(booking)
                        .roomType(roomSelection.getRoomType())
                        .count(roomSelection.getCount())
                        .build();
                booking.getBookedRooms().add(bookedRoom);
            }
        }

        return booking;
    }

}
