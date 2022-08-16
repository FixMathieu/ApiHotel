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

    @ManyToOne()
    private City city;

    @OneToOne
    private Image imageModel;

}
