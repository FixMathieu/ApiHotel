package fr.fms.apihotel.security.payload;

import fr.fms.apihotel.entities.Users;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class JwtResponse {
    private String accessToken;
    private String refreshToken;
    //    private String username;
//    private String password;
    private Users user;
    private Collection<GrantedAuthority> authorities;
}
