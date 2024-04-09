package br.com.eduardo.ponciano.travel.mvc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.eduardo.ponciano.travel.mvc.model.Room;

@Repository
public interface RoomRepository extends JpaRepository<Room, Long> {
}
