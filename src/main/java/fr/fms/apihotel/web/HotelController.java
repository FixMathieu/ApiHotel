package fr.fms.apihotel.web;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import fr.fms.apihotel.entities.Hotel;
import fr.fms.apihotel.errors.RecordNotFoundException;
import fr.fms.apihotel.service.ImplCityService;
import fr.fms.apihotel.service.ImplHotelService;
import fr.fms.apihotel.service.ImplImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/api")
public class HotelController {
    @Autowired
    private ImplHotelService implHotelService;
    @Autowired
    private ImplCityService implCityService;
    @Autowired
    private ImplImageService implImageService;

    @GetMapping("/hotels")
    public List<Hotel> listOfHotels(){return implHotelService.getAll();}

//    @GetMapping("/hotel/{id}")
//    public List<Hotel> getCitiesByCity(@PathVariable("id") long id) {
//        return implHotelService.getByCity(id);
//    }
    @PostMapping("/hotels")
    public ResponseEntity<Error> saveHotel(@RequestParam("image") MultipartFile image, @RequestParam("hotel") String hotelJson, @RequestHeader("Authorization") String authorization) throws JsonProcessingException {
        Hotel hotel = new ObjectMapper().readValue(hotelJson, Hotel.class);
        hotel.setCity(implCityService.getOneById(hotel.getCity().getId()).get());
        if (hotel.getImage() != null) {
            hotel.setImage(image.getOriginalFilename());
        } else {
            hotel.setImage("noimage.png");
        }
        try {
            implImageService.save(image);
        } catch (Exception e) {
            String message = "Could not upload the file: " + image.getOriginalFilename() + "!";
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new Error(message));
        }
        implHotelService.save(hotel);
        return null;
    }

    @PutMapping("/hotel/{id}")
    public void updateHotel(@RequestParam("image") MultipartFile image, @RequestParam("hotel") String hotelJson, @RequestHeader("Authorization") String authorization) throws JsonProcessingException {
        Hotel hotel = new ObjectMapper().readValue(hotelJson, Hotel.class);
       hotel.setCity(implCityService.getOneById(hotel.getCity().getId()).get());
        if (hotel.getImage() != null && hotel.getImage() != image.getOriginalFilename()) {
            hotel.setImage(image.getOriginalFilename());
        } else {
            hotel.setImage("noimage.png");
        }
        try {
            implImageService.save(image);
        } catch (Exception e) {
            String message = "Could not upload the file: " + image.getOriginalFilename() + "!";
        }
        implHotelService.save(hotel);
    }
    @DeleteMapping("/hotels/{id}")
    public void deleteHotel(@PathVariable("id") long id, @RequestHeader("Authorization") String authorization) throws IOException {
        Hotel hotel= implHotelService.getOneById(id).get();
        implHotelService.delete(id);
        if (hotel.getImage() != "noimage.png") {
            Files.delete(Paths.get("uploads").resolve(hotel.getImage()));
        }
    }

    @GetMapping("/hotel/{id}")
    public Hotel getHotelById(@PathVariable("id") long id) {
        return implHotelService.getOneById(id).orElseThrow(
                () -> new RecordNotFoundException("Id de formation " + id + " n'existe pas."));
    }

    @GetMapping("/hotelImage/{id}")
    public byte[] getHotelImage(@PathVariable("id") Long id) throws Exception {
        Hotel hotel = implHotelService.getOneById(id).get();
        return Files.readAllBytes(Paths.get("uploads").resolve(hotel.getImage()));
    }
//    @GetMapping("/city/{id}/hotels")
//    public List<Hotel> getHotelByCity(@PathVariable("id") long id) {
//        return implHotelService.getByCity(id);
//    }
}
