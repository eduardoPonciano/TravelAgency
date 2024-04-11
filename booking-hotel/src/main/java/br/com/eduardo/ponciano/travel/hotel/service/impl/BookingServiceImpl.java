package br.com.eduardo.ponciano.travel.hotel.service.impl;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import br.com.eduardo.ponciano.travel.commons.model.dto.AddressDTO;
import br.com.eduardo.ponciano.travel.commons.model.dto.BookingDTO;
import br.com.eduardo.ponciano.travel.commons.model.dto.BookingInitiationDTO;
import br.com.eduardo.ponciano.travel.commons.model.dto.HotelDTO;
import br.com.eduardo.ponciano.travel.hotel.mapper.HotelMapper;
import br.com.eduardo.ponciano.travel.hotel.model.BookedRoom;
import br.com.eduardo.ponciano.travel.hotel.model.Booking;
import br.com.eduardo.ponciano.travel.hotel.model.Hotel;
import br.com.eduardo.ponciano.travel.hotel.model.RoomSelectionDTO;
import br.com.eduardo.ponciano.travel.hotel.repository.BookingRepository;
import br.com.eduardo.ponciano.travel.hotel.service.BookingService;
import br.com.eduardo.ponciano.travel.hotel.service.HotelService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BookingServiceImpl implements BookingService {

    private final BookingRepository bookingRepository;
    private final HotelService hotelService;


    @Override
    @Transactional
    public Booking saveBooking(BookingInitiationDTO bookingInitiationDTO, Long userId) {
        validateBookingDates(bookingInitiationDTO.getCheckinDate(), bookingInitiationDTO.getCheckoutDate());

        HotelDTO hotel = hotelService.findByParameters(bookingInitiationDTO.getHotelId(),null,null);
        if(hotel==null) {
        	new EntityNotFoundException("Hotel not found with ID: " + bookingInitiationDTO.getHotelId());
        }

        Booking booking = mapBookingInitDtoToBookingModel(bookingInitiationDTO, hotel);

        return bookingRepository.save(booking);
    }

    @Override
    @Transactional
    public BookingDTO confirmBooking(BookingInitiationDTO bookingInitiationDTO, Long customerId) {
        Booking savedBooking = this.saveBooking(bookingInitiationDTO, customerId);
        bookingRepository.save(savedBooking);
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

//        List<RoomSelectionDTO> roomSelections = booking.getBookedRooms().stream()
//                .map(room -> RoomSelectionDTO.builder()
//                        .roomType(room.getRoomType())
//                        .count(room.getCount())
//                        .build())
//                .collect(Collectors.toList());

        return BookingDTO.builder()
                .id(booking.getId())
                .confirmationNumber(booking.getConfirmationNumber())
                .bookingDate(booking.getBookingDate())
                 .hotelId(booking.getHotel().getId())
                .checkinDate(booking.getCheckinDate())
                .checkoutDate(booking.getCheckoutDate())
                .hotelName(booking.getHotel().getName())
                .build();
    }

    private Booking mapBookingInitDtoToBookingModel(BookingInitiationDTO bookingInitiationDTO, HotelDTO hotel) {
        Booking booking = Booking.builder()
                .hotel(HotelMapper.dtoToEntity(hotel))
                .checkinDate(bookingInitiationDTO.getCheckinDate())
                .checkoutDate(bookingInitiationDTO.getCheckoutDate())
                .build();

        for (br.com.eduardo.ponciano.travel.commons.model.dto.RoomSelectionDTO roomSelection : bookingInitiationDTO.getRoomSelections()) {
            if (roomSelection.getCount() > 0) {
                BookedRoom bookedRoom = BookedRoom.builder()
                        .booking(booking)
//                        .typeBed(roomSelection.getRoomType())
//                        .roomType(roomSelection.getRoomType())
                        .count(roomSelection.getCount())
                        .build();
                booking.getBookedRooms().add(bookedRoom);
            }
        }

        return booking;
    }

}
