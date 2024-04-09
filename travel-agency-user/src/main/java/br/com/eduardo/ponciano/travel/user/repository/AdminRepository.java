package br.com.eduardo.ponciano.travel.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.eduardo.ponciano.travel.user.model.Admin;

@Repository
public interface AdminRepository extends JpaRepository<Admin, Long> {
}
