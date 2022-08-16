package fr.fms.apihotel.business;

import fr.fms.apihotel.entities.City;
import fr.fms.apihotel.entities.Hotel;
import fr.fms.apihotel.entities.Image;
import fr.fms.apihotel.entities.Users;

import java.util.List;
import java.util.Optional;

public interface IBusiness {
    Image saveImage(Image image);

    Image getImageByName(String name);

    Hotel addHotel(Hotel hotel);

    List<Hotel> getAllHotel();

    Optional<Hotel> getHotelById(long id);

    void deleteHotelById(long id);

    City add(City city);

    City getOneById(long id);

    void delete(long id);

    List<City> getAll();

    public Optional<Users> getUserByMail(String mail) throws Exception;
}
