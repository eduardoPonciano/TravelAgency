package br.com.eponciano.ms.booking.hotel.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.eponciano.ms.booking.hotel.model.Room;

@Repository
public interface RoomRepository extends JpaRepository<Room, Long> {
}
