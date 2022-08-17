package fr.fms.apihotel;

import fr.fms.apihotel.dao.*;
import fr.fms.apihotel.entities.City;
import fr.fms.apihotel.entities.Hotel;
import fr.fms.apihotel.entities.Role;
import fr.fms.apihotel.entities.Users;
import fr.fms.apihotel.security.SecurityConfig;
import fr.fms.apihotel.service.ImplRoleService;
import fr.fms.apihotel.service.ImplUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;



import java.util.ArrayList;
import java.util.List;



@SpringBootApplication
public class ApiHotelApplication implements CommandLineRunner {
	@Autowired
	CityRepository cityRepository;
	@Autowired
	HotelRepository hotelRepository;


	@Autowired
	ImplRoleService implRoleService;

	@Autowired
	private ImplUserService implUserService;
	@Autowired
	private SecurityConfig security;
	public static void main(String[] args) {
		SpringApplication.run(ApiHotelApplication.class, args);
	}


	@Override
	public void run(String... args) throws Exception {
		populateUsers();
		populateHotels();
	}
		public void populateHotels() {

		City toulouse = cityRepository.save(new City(null, "Toulouse"));
		City paris = cityRepository.save(new City(null, "Paris"));
		City bordeaux = cityRepository.save(new City(null, "Bordeaux"));
		City lyon = cityRepository.save(new City(null, "Lyon"));
		City nancy = cityRepository.save(new City(null, "Nancy"));

		Hotel one = hotelRepository.save(new Hotel(null,"Premier","123 rue des lilas","0501020304","2 étoiles",5,53.5,"null",bordeaux));
		Hotel two = hotelRepository.save(new Hotel(null,"Deuxième","123 rue des chênes","0501020305","3 étoiles",6,83.5,"null",bordeaux));
		Hotel three = hotelRepository.save(new Hotel(null,"Troisième","123 rue des champs","0501020306","4 étoiles",10,153.5,"null",bordeaux));
		Hotel four = hotelRepository.save(new Hotel(null,"Quatrième","123 rue des plages","0501020304","5 étoiles",15,353.5,"null",bordeaux));

		Hotel one1 = hotelRepository.save(new Hotel(null,"Premier","123 rue des lilas","0501020304","2 étoiles",5,53.5,"null",toulouse));
		Hotel two2 = hotelRepository.save(new Hotel(null,"Deuxième","123 rue des chênes","0501020305","3 étoiles",6,83.5,"null",toulouse));
		Hotel three3 = hotelRepository.save(new Hotel(null,"Troisième","123 rue des champs","0501020306","4 étoiles",10,153.5,"null",toulouse));
		Hotel four4 = hotelRepository.save(new Hotel(null,"Quatrième","123 rue des plages","0501020304","5 étoiles",15,353.5,"null",toulouse));

		Hotel one11 = hotelRepository.save(new Hotel(null,"Premier","123 rue des lilas","0501020304","2 étoiles",5,53.5,"null",paris));
		Hotel two22 = hotelRepository.save(new Hotel(null,"Deuxième","123 rue des chênes","0501020305","3 étoiles",6,83.5,"null",paris));
		Hotel three33 = hotelRepository.save(new Hotel(null,"Troisième","123 rue des champs","0501020306","4 étoiles",10,153.5,"null",paris));
		Hotel four44 = hotelRepository.save(new Hotel(null,"Quatrième","123 rue des plages","0501020304","5 étoiles",15,353.5,"null",paris));

		Hotel one111 = hotelRepository.save(new Hotel(null,"Premier","123 rue des lilas","0501020304","2 étoiles",5,53.5,"null",lyon));
		Hotel two222 = hotelRepository.save(new Hotel(null,"Deuxième","123 rue des chênes","0501020305","3 étoiles",6,83.5,"null",lyon));
		Hotel three333 = hotelRepository.save(new Hotel(null,"Troisième","123 rue des champs","0501020306","4 étoiles",10,153.5,"null",lyon));
		Hotel four444 = hotelRepository.save(new Hotel(null,"Quatrième","123 rue des plages","0501020304","5 étoiles",15,353.5,"null",lyon));

		Hotel one1111 = hotelRepository.save(new Hotel(null,"Premier","123 rue des lilas","0501020304","2 étoiles",5,53.5,"null",nancy));
		Hotel two2222 = hotelRepository.save(new Hotel(null,"Deuxième","123 rue des chênes","0501020305","3 étoiles",6,83.5,"null",nancy));
		Hotel three3333 = hotelRepository.save(new Hotel(null,"Troisième","123 rue des champs","0501020306","4 étoiles",10,153.5,"null",nancy));
		Hotel four4444 = hotelRepository.save(new Hotel(null,"Quatrième","123 rue des plages","0501020304","5 étoiles",15,353.5,"null",nancy));
	}

	public void populateUsers() {
		Role admin = implRoleService.save(new Role(null, "ADMIN"));
		Role user = implRoleService.save(new Role(null, "USER"));

		List<Role> mat = new ArrayList<>();
		mat.add(admin);
		mat.add(user);

		List<Role> tristan = new ArrayList<>();
		tristan.add(user);

		implUserService.save(new Users(null, "j.delmerie@live.fr", "del",  security.encodePassword("mdp1"), true, mat));
		implUserService.save(new Users(null, "mama@live.fr", "mamacita", security.encodePassword("mdp2"),true, tristan));
	}
}
