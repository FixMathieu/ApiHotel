package fr.fms.apihotel.service;

import fr.fms.apihotel.dao.CityRepository;
import fr.fms.apihotel.entities.City;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

public class ImplCityService implements IService<City>{
    @Autowired
    CityRepository cityRepository;
    @Override
    public List<City> getAll(){return cityRepository.findAll();}

    @Override
    public Optional<City> getOneById(long id){return  cityRepository.findById(id);}

    @Override
    public City save(City obj){ return null;}
    @Override
    public void delete(long id){}
}
