package fr.fms.apihotel.web;

import com.auth0.jwt.JWT;
import fr.fms.apihotel.dao.UsersRepository;
import fr.fms.apihotel.entities.Users;
import fr.fms.apihotel.security.JwtUtils;
import fr.fms.apihotel.security.payload.JwtResponse;
import fr.fms.apihotel.security.payload.LoginRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.core.userdetails.User;
import com.auth0.jwt.algorithms.Algorithm;

import java.util.Date;
import java.util.stream.Collectors;

@CrossOrigin("*")
@RestController
@RequestMapping("/api")
public class AuthController {
    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UsersRepository usersRepository;

    @PostMapping("/signin")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        //creation authentification
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        User user = (User) authentication.getPrincipal();

        //Generate secret algorithm
        Algorithm algorithm = Algorithm.HMAC256(JwtUtils.SECRET);

        //Generate access Token
        String accessToken = JWT.create()
                .withSubject(user.getUsername())
                .withExpiresAt(new Date(System.currentTimeMillis() + 30 * 60 * 1000))
                .withClaim("roles", user.getAuthorities().stream().map(roles ->
                        roles.getAuthority()).collect(Collectors.toList()))
                .sign(algorithm);

        //Generate refresh Token
        String refreshToken = JWT.create()
                .withSubject(user.getUsername())
                .withExpiresAt(new Date(System.currentTimeMillis() + 15 * 60 * 1000))
//                .withIssuer(request.getRequestURL().toString())
                .sign(algorithm);

        Users users = usersRepository.findByEmail(user.getUsername());

        //Generate response
        JwtResponse response = new JwtResponse(accessToken, refreshToken, users, user.getAuthorities());
        return ResponseEntity.ok(response);
    }
}