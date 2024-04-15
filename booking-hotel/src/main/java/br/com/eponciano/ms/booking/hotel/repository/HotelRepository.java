package br.com.eponciano.ms.booking.hotel.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.eponciano.ms.booking.hotel.mapper.HotelMapper;
import br.com.eponciano.ms.booking.hotel.model.Hotel;
import jakarta.persistence.EntityNotFoundException;

@Repository
public interface HotelRepository extends JpaRepository<Hotel, Long> {

    Optional<Hotel> findByName(String name);

    @Query("SELECT h FROM Hotel h WHERE h.address.city = :city")
    List<Hotel> findHotelsByCity(@Param("city") String city);

    @Query("SELECT h FROM Hotel h WHERE "
    		+ "(:id is not null and h.id = :id)"
    		+ "and (:name is not null and h.name = :name)"
    		+ "and (:managerId is not null and h.managerId = :managerId)"
    		)
    Hotel findByParameters(@Param("id") Long id, @Param("name") String name, @Param("managerId") Long managerId);
    
    @Query("SELECT h FROM Hotel h WHERE "
    		+ "(:managerId is not null and h.managerId = :managerId)")
    List<Hotel> findAllByHotelManagerId(@Param("managerId") Long managerId);
//    @Query("SELECT h " +
//            "FROM Hotel h " +
//            "JOIN h.rooms r " +
////            "LEFT JOIN Availability a ON a.room.id = r.id " +
////            "AND a.date >= :checkinDate AND a.date < :checkoutDate " +
////            "WHERE h.address.city = :city " +
////            "AND (a IS NULL OR a.availableRooms > 0) " +
//            "GROUP BY h.id, r.id " +
//            "HAVING COUNT(DISTINCT a.date) + SUM(CASE WHEN a IS NULL THEN 1 ELSE 0 END) = :numberOfDays")
//    List<Hotel> findHotelsWithAvailableRooms(@Param("city") String city,
//                                             @Param("checkinDate") LocalDate checkinDate,
//                                             @Param("checkoutDate") LocalDate checkoutDate,
//                                             @Param("numberOfDays") Long numberOfDays);
//
//    @Query("SELECT h " +
//            "FROM Hotel h " +
//            "WHERE h.address.city = :city " +
////            "AND NOT EXISTS (" +
////            "   SELECT 1 " +
////            "   FROM Availability a " +
////            "   WHERE a.room.hotel.id = h.id " +
////            "   AND a.date >= :checkinDate AND a.date < :checkoutDate" +
////            ")"+
//             "")
//    List<Hotel> findHotelsWithoutAvailabilityRecords(@Param("city") String city,
//                                                     @Param("checkinDate") LocalDate checkinDate,
//                                                     @Param("checkoutDate") LocalDate checkoutDate);
//
//    @Query("SELECT h " +
//            "FROM Hotel h " +
//            "JOIN h.rooms r " +
////            "LEFT JOIN Availability a ON r.id = a.room.id " +
////            "AND a.date >= :checkinDate AND a.date < :checkoutDate " +
////            "WHERE h.address.city = :city " +
////            "AND (a IS NULL OR a.availableRooms > 0) " +
//            "GROUP BY h.id " +
//            "HAVING COUNT(DISTINCT a.date) < :numberOfDays " +
//            "AND COUNT(DISTINCT CASE WHEN a.availableRooms > 0 THEN a.date END) > 0")
//    List<Hotel> findHotelsWithPartialAvailabilityRecords(@Param("city") String city,
//                                                         @Param("checkinDate") LocalDate checkinDate,
//                                                         @Param("checkoutDate") LocalDate checkoutDate,
//                                                         @Param("numberOfDays") Long numberOfDays);

}
