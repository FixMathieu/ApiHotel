package fr.fms.apihotel.web;




import fr.fms.apihotel.entities.City;
import fr.fms.apihotel.service.ImplCityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.Optional;

@CrossOrigin("*")
@RestController
@RequestMapping("/api")
public class CityController {

    @Autowired
    private ImplCityService implCityService;

    @GetMapping("/cities")
    public List<City> listOfCity(){
        return implCityService.getAll();
    }
    @GetMapping("/city/{id}")
    public City getHotelsByCity(@PathVariable("id") long id){
        Optional<City> city = implCityService.getOneById(id);
        if(city.isPresent()){
            return city.get();
        }
        return null;
    }

}

