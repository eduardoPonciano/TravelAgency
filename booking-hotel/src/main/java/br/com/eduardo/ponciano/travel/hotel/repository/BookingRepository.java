package br.com.eduardo.ponciano.travel.hotel.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.eduardo.ponciano.travel.hotel.model.Booking;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {

    List<Booking> findBookingsByCustomerId(Long customerId);

    Optional<Booking> findBookingByIdAndCustomerId(Long bookingId, Long customerId);

    List<Booking> findBookingsByHotelId(Long hotelId);

}
