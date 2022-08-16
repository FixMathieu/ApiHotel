package fr.fms.apihotel;

import fr.fms.apihotel.dao.*;
import fr.fms.apihotel.entities.City;
import fr.fms.apihotel.entities.Hotel;
import fr.fms.apihotel.entities.Users;
import fr.fms.entities.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ApiHotelApplication {
	@Autowired
	CityRepository cityRepository;
	@Autowired
	HotelRepository hotelRepository;
	@Autowired
	UsersRepository usersRepository;
	@Autowired
	RoleRepository roleRepository;
	@Autowired
	ImageRepository imageRepository;
	public static void main(String[] args) {
		SpringApplication.run(ApiHotelApplication.class, args);
	}


	@Override
	public void run(String... args) throws Exception {
		Role superAdmin = roleRepository.save(new Role(null, "SUPERADMIN"));
		Role admin = roleRepository.save(new Role(null, "ADMIN"));
		Role user = roleRepository.save(new Role(null, "USER"));

		Users mat =usersRepository.save(new Users(null,"mat@gmail.com","123",true));
		Users tristan =usersRepository.save(new Users(null,"tristan@gmail.com","123",true));
		Users martial =usersRepository.save(new Users(null,"martial@gmail.com","123",true));

		City toulouse = cityRepository.save(new City(null, "Toulouse"));
		City paris = cityRepository.save(new City(null, "Paris"));
		City bordeaux = cityRepository.save(new City(null, "Bordeaux"));
		City lyon = cityRepository.save(new City(null, "Lyon"));
		City nancy = cityRepository.save(new City(null, "Nancy"));

		Hotel one = hotelRepository.save(new Hotel(null,"Premier","123 rue des lilas","0501020304","2 étoiles",5,53.5,bordeaux,null));
		Hotel two = hotelRepository.save(new Hotel(null,"Deuxième","123 rue des chênes","0501020305","3 étoiles",6,83.5,bordeaux,null));
		Hotel three = hotelRepository.save(new Hotel(null,"Troisième","123 rue des champs","0501020306","4 étoiles",10,153.5,bordeaux,null));
		Hotel four = hotelRepository.save(new Hotel(null,"Quatrième","123 rue des plages","0501020304","5 étoiles",15,353.5,bordeaux,null));

		Hotel one1 = hotelRepository.save(new Hotel(null,"Premier","123 rue des lilas","0501020304","2 étoiles",5,53.5,toulouse,null));
		Hotel two2 = hotelRepository.save(new Hotel(null,"Deuxième","123 rue des chênes","0501020305","3 étoiles",6,83.5,toulouse,null));
		Hotel three3 = hotelRepository.save(new Hotel(null,"Troisième","123 rue des champs","0501020306","4 étoiles",10,153.5,toulouse,null));
		Hotel four4 = hotelRepository.save(new Hotel(null,"Quatrième","123 rue des plages","0501020304","5 étoiles",15,353.5,toulouse,null));

		Hotel one11 = hotelRepository.save(new Hotel(null,"Premier","123 rue des lilas","0501020304","2 étoiles",5,53.5,paris,null));
		Hotel two22 = hotelRepository.save(new Hotel(null,"Deuxième","123 rue des chênes","0501020305","3 étoiles",6,83.5,paris,null));
		Hotel three33 = hotelRepository.save(new Hotel(null,"Troisième","123 rue des champs","0501020306","4 étoiles",10,153.5,paris,null));
		Hotel four44 = hotelRepository.save(new Hotel(null,"Quatrième","123 rue des plages","0501020304","5 étoiles",15,353.5,paris,null));

		Hotel one111 = hotelRepository.save(new Hotel(null,"Premier","123 rue des lilas","0501020304","2 étoiles",5,53.5,lyon,null));
		Hotel two222 = hotelRepository.save(new Hotel(null,"Deuxième","123 rue des chênes","0501020305","3 étoiles",6,83.5,lyon,null));
		Hotel three333 = hotelRepository.save(new Hotel(null,"Troisième","123 rue des champs","0501020306","4 étoiles",10,153.5,lyon,null));
		Hotel four444 = hotelRepository.save(new Hotel(null,"Quatrième","123 rue des plages","0501020304","5 étoiles",15,353.5,lyon,null));
	}

}
