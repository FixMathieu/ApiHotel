package fr.fms.apihotel.dao;

import fr.fms.apihotel.entities.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HotelRepository extends JpaRepository<Hotel,Long> {

    List<Hotel> findByNameContains(String keyWord);
    List<Hotel> findByCityId(long id);
}
