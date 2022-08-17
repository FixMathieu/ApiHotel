package fr.fms.apihotel.entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @author Stagiaire 10P
 *
 */
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Role implements Serializable {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;



    @ManyToMany
    @JoinTable(
            name = "users_role",
            joinColumns = {@JoinColumn(name = "roleId")},
            inverseJoinColumns = {@JoinColumn(name = "usersId")}
    )
    @JsonIgnore
    private List<Users> users;


    public Role(Long id, String name) {
        this.id = id;
        this.name = name;
    }
    public Role( String name) {
        this.name = name;
    }
}
