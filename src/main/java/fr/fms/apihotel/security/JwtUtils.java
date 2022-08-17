package fr.fms.apihotel.security;

import org.springframework.stereotype.Component;

@Component
public class JwtUtils {
    public static final String SECRET = "secret";
    public static final String AUTH_HEADER = "Authorization";

}
