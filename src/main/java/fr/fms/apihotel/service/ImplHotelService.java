package fr.fms.apihotel.service;

import fr.fms.apihotel.dao.HotelRepository;
import fr.fms.apihotel.entities.Hotel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ImplHotelService implements IService<Hotel> {
    @Autowired
    HotelRepository hotelRepository;
    @Override
    public List<Hotel> getAll() {return hotelRepository.findAll();}

    @Override
    public Optional<Hotel> getOneById(long id) {
        return hotelRepository.findById(id);
    }


    @Override
    public Hotel save(Hotel hotel) {
        return hotelRepository.save(hotel);
    }

    @Override
    public void delete(long id) { hotelRepository.deleteById(id);

    }

    public List<Hotel> getByCity(long id) {return hotelRepository.findByCityId(id);
    }
}
