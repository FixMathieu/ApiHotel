package fr.fms.apihotel.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Data @AllArgsConstructor @NoArgsConstructor @ToString @Builder
public class City implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @OneToMany(mappedBy = "city", fetch = FetchType.EAGER)
    @JsonIgnore
    private List<Hotel> hotels = new java.util.ArrayList<>();

    public List<Hotel> getHotels() {
        return hotels;
    }

    public void setHotels(List<Hotel> hotels) {
        this.hotels = hotels;
    }


    public City(Long id, String name) {
        this.id = id;
        this.name = name;

    }
}
