package fr.fms.apihotel.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import fr.fms.apihotel.entities.Role;
import java.util.List;
import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role,Long> {
}
