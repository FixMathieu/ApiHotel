package fr.fms.apihotel.web;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import fr.fms.apihotel.entities.Hotel;
import fr.fms.apihotel.service.ImplCityService;
import fr.fms.apihotel.service.ImplHotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/api")
public class HotelController {
    @Autowired
    private ImplHotelService implHotelService;
    @Autowired
    private ImplCityService implCityService;

    @GetMapping("/hotels")
    public List<Hotel> listOfHotels(){return implHotelService.getAll();}

    @GetMapping("/hotel/{id}")
    public List<Hotel> getCitiesByCat(@PathVariable("id") long id) {
        return implHotelService.getByCity(id);
    }
    @PostMapping("/hotels")
    public ResponseEntity<Error> saveHotel(@RequestParam("image") MultipartFile image, @RequestParam("hotel") String hotelJson, @RequestHeader("Authorization") String authorization) throws JsonProcessingException {
        Hotel hotel = new ObjectMapper().readValue(hotelJson, Hotel.class);
        hotel.setCity(implCityService.getOneById(hotel.getCity().getId()).get());
        if (hotel.getImage() != null) {
            hotel.setImage(image.getOriginalFilename());
        } else {
            training.setImage("noimage.png");
        }
        try {
            implImageService.save(image);
        } catch (Exception e) {
            String message = "Could not upload the file: " + image.getOriginalFilename() + "!";
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new Error(message));
        }
        implTrainingService.save(training);
        return null;
    }

}
