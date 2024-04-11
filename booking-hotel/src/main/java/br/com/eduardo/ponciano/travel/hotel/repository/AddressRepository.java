package br.com.eduardo.ponciano.travel.hotel.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.eduardo.ponciano.travel.hotel.model.Address;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {
}
