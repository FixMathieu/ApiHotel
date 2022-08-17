package fr.fms.apihotel.business;

import fr.fms.apihotel.dao.CityRepository;
import fr.fms.apihotel.dao.HotelRepository;
import fr.fms.apihotel.dao.ImageRepository;
import fr.fms.apihotel.dao.UsersRepository;
import fr.fms.apihotel.entities.City;
import fr.fms.apihotel.entities.Hotel;
import fr.fms.apihotel.entities.Image;
import fr.fms.apihotel.entities.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
@Service
public class IBusinessImpl implements IBusiness{
    @Autowired
    UsersRepository usersRepository;
    @Autowired
    CityRepository cityRepository;
    @Autowired
    HotelRepository hotelRepository;

    @Autowired
    private ImageRepository imageRepository;

    @Override
    public Image saveImage(Image image) {
        return imageRepository.save(image);
    }

    @Override
    public Image getImageByName(String name) {
        return imageRepository.findByName(name);
    }

    @Override
    public Hotel addHotel(Hotel hotel){return hotelRepository.save(hotel);}

    @Override
    public List<Hotel> getAllHotel(){return hotelRepository.findAll();}

    @Override
    public Optional<Hotel> getHotelById(long id){return hotelRepository.findById(id);}

    @Override
    public void deleteHotelById(long id){hotelRepository.deleteById(id);}

    @Override
    public City add(City city) {
        return cityRepository.save(city);
    }

    @Override
    public City getOneById(long id) {
        return cityRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Resource not found"));
    }

    @Override
    public void delete(long id) {
        cityRepository.deleteById(id);
    }

    @Override
    public List<City> getAll() {
        return cityRepository.findAll();
    }

    @Override
    public Optional<Users> getUserByMail(String mail) throws Exception {
        return Optional.empty();
    }


}
