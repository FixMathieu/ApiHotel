package fr.fms.apihotel.entities;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class Hotel implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String address;
    private String phone;
    private String star;
    private int nbrRoom;
    private double price;
    private String image;
    @ManyToOne()
    @JoinColumn(name="city_id")
    private City city;

    @Override
    public  String toString(){
        return "Hotel{"+
                "id=" + id +
                ", name='" + name + '\''+
                ", address='" + address +'\'' +
                ", phone='"+ phone+'\''+
                ", star='"+ star+'\''+
                ", nbrRoom='"+ nbrRoom+'\''+
                ", price='"+ price+'\''+
                ", image='"+ image +'\''+
                ", city="+city+'}';
    }





}
