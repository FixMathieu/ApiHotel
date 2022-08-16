package fr.fms.apihotel.entities;



import java.util.Collection;
import java.util.List;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Stagiaires10P
 *
 */
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Email
    private String mail;

    @NotNull
    private String password;

    @NotNull
    private String username;

    @NotNull
    private Boolean active;

    @ManyToMany
    @JoinTable(
            name = "users_role",
            joinColumns = {@JoinColumn(name = "usersId")},
            inverseJoinColumns = {@JoinColumn(name = "roleId")}
    )
    @JsonIgnore
    private List<fr.fms.entities.Role> role;

    /**
     * @param id
     * @param password
     */
    public Users(Long id, String mail, String password) {
        this.id = id;
        this.mail = mail;
        this.password = password;
    }

    /**
     * @param id
     * @param mail
     * @param active
     */
    public Users(Long id, String mail, @NotNull Boolean active) {
        this.id = id;
        this.mail = mail;
        this.active = active;
    }

    /**
     * @param id
     * @param mail
     * @param password
     * @param active
     */
    public Users(Long id, @NotNull String mail, @NotNull String password, @NotNull Boolean active) {
        this.id = id;
        this.mail = mail;
        this.password = password;
        this.active = active;
    }


    /**
     * @param id
     * @param mail
     */
    public Users(Long id, @NotNull String mail) {

        this.mail = mail;
    }
    public Users(Long id,String username, @NotNull String mail,String password,Boolean active,List<fr.fms.entities.Role> role) {
        this.id = id;
        this.username=username;
        this.mail = mail;
        this.password=password;
        this.active=active;
        this.role=role;
    }
    public void setRoles(List<fr.fms.entities.Role> roles) {
        this.role=roles;
    }

    public List<fr.fms.entities.Role> getRoles() {

        return role;
    }
}
