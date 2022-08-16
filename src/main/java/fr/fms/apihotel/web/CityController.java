package fr.fms.apihotel.web;

import fr.fms.apihotel.business.IBusinessImpl;
import fr.fms.apihotel.dao.CityRepository;
import fr.fms.apihotel.entities.City;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/city")
public class CityController {

    @Autowired
    private IBusinessImpl iBusiness;

    /**
     * Add a new city
     *
     * @param city
     */
    @PostMapping("/save")
    @ResponseStatus(HttpStatus.CREATED)
    public City add(@RequestBody City city) {
        return  iBusiness.add(city);
    }

    /**
     * Update a city by id
     *
     * @param id
     * @param city
     */
    @PutMapping("/update/{id}")
    public void update(@PathVariable("id") long id, @RequestBody City city) {
        City cityToUpdate = iBusiness.getOneById(id);
        cityToUpdate.setName(city.getName());

        iBusiness.add(cityToUpdate);
    }

    /**
     * Delete city by id
     *
     * @param id
     */
    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable("id") long id) {
        iBusiness.delete(id);
    }

    /**
     * Return a city by id
     *
     * @param id
     * @return
     */
    @GetMapping("/get/{id}")
    public City getOne(@PathVariable("id") long id) {
        return iBusiness.getOneById(id);
    }

    /**
     * Return list of all cities
     * @return
     */
    @GetMapping("/all")
    public List<City> getAll() {
        return iBusiness.getAll();
    }
}

