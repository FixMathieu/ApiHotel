package fr.fms.apihotel.dao;

import fr.fms.apihotel.entities.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsersRepository extends JpaRepository<Users, Long> {
    public Optional<Users> findByMail(String mail);

    Optional<Users> findByUsername(String username);

    Boolean existsByUsername(String username);

    Boolean existsByMail(String mail);
}
