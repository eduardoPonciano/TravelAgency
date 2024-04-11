package br.com.eponciano.ms.booking.mvc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.eponciano.ms.booking.mvc.model.Role;
import br.com.eponciano.ms.booking.mvc.model.enums.RoleType;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

    Role findByRoleType(RoleType roleType);
}
