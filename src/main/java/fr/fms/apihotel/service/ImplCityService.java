package fr.fms.apihotel.service;

import fr.fms.apihotel.dao.CityRepository;
import fr.fms.apihotel.entities.City;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class ImplCityService implements IService<City>{
    @Autowired
    CityRepository cityRepository;
    @Override
    public List<City> getAll(){return cityRepository.findAll();}

    @Override
    public Optional<City> getOneById(long id){return  cityRepository.findById(id);}

    @Override
    public City save(City city){ return cityRepository.save(city);}
    @Override
    public void delete(long id){}
}
