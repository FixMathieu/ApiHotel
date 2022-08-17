package fr.fms.apihotel.dao;

import fr.fms.apihotel.entities.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsersRepository extends JpaRepository<Users, Long> {




    public Users findByEmail(String email);
}
