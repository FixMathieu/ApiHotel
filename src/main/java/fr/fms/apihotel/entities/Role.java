package fr.fms.entities;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;
import fr.fms.apihotel.entities.Users;
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
public class Role {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String role;



    @ManyToMany
    @JoinTable(
            name = "users_role",
            joinColumns = {@JoinColumn(name = "roleId")},
            inverseJoinColumns = {@JoinColumn(name = "usersId")}
    )
    @JsonIgnore
    private List<Users> users;

    /**
     * @param id
     * @param role
     */
    public Role(Long id, String role) {
        this.id = id;
        this.role = role;
    }
    public Role( String role) {
        this.role = role;
    }
}
