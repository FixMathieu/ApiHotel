package fr.fms.apihotel.dao;

import fr.fms.apihotel.entities.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CityRepository extends JpaRepository<City, Long> {

    List<City> findByNameContains(String keyWord);

    City findByName(String cityName);

}
