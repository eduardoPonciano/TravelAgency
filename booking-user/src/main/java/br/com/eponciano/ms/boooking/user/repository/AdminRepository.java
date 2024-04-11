package br.com.eponciano.ms.boooking.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.eponciano.ms.boooking.user.model.Admin;

@Repository
public interface AdminRepository extends JpaRepository<Admin, Long> {
}
