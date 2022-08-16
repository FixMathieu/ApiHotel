package fr.fms.apihotel.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface RoleRepository extends JpaRepository<fr.fms.entities.Role, Long> {
    List<fr.fms.entities.Role> findAll();

    Optional<fr.fms.entities.Role> findByRole(String role);

}
