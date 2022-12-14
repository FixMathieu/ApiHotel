package fr.fms.apihotel.security;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private AuthenticationManager authenticationManager;

    public JwtAuthenticationFilter(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        System.out.println("User tente une connexion");
        System.out.println(username + " " + password);
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username, password);
        System.out.println(authenticationToken);
        return authenticationManager.authenticate(authenticationToken);
    }
    //    @Override
//    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
//        User user = (User) authResult.getPrincipal();
//        System.out.println("Connexion réussie");
//        Algorithm algorithm = Algorithm.HMAC256(JwtUtils.SECRET);
//        String accessToken = JWT.create()
//                .withSubject(user.getUsername())
////                .withExpiresAt(new Date(System.currentTimeMillis() + 1 * 60 * 1000))
//                .withIssuer(request.getRequestURL().toString())
//                .withClaim("roles", user.getAuthorities().stream().map(role -> role.getAuthority()).collect(Collectors.toList()))
//                .sign(algorithm);
//
//        String refreshToken = JWT.create()
//                .withSubject(user.getUsername())
////                .withExpiresAt(new Date(System.currentTimeMillis() + 15 * 60 * 1000))
//                .withIssuer(request.getRequestURL().toString())
//                .sign(algorithm);
//        Map<String, String> idToken = new HashMap<>();
//        idToken.put("access-token", accessToken);
//        idToken.put("refresh-token", refreshToken);
//        response.setContentType("application/json");
//        new ObjectMapper().writeValue(response.getOutputStream(), idToken);
//    }

}
