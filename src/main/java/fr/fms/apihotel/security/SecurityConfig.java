package fr.fms.apihotel.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.header.writers.StaticHeadersWriter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import javax.sql.DataSource;
import java.util.Arrays;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    DataSource dataSource;


    String usersByUsernameQuery = "select email, password, enable from users where email = ?";
    String authoritiesByUsernameQuery = "SELECT u.email, r.name from users AS u \r\n" + "INNER JOIN users_roles ur ON u.id = ur.users_id \r\n" + "INNER JOIN role r ON ur.roles_id = r.id \r\n" + "where email = ?";

    public String encodePassword(String password) {
        PasswordEncoder passwordEncoder = passwordEncoder();
        return passwordEncoder.encode(password);
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        PasswordEncoder passwordEncoder = passwordEncoder();
        auth.jdbcAuthentication().dataSource(dataSource).usersByUsernameQuery(usersByUsernameQuery).authoritiesByUsernameQuery(authoritiesByUsernameQuery).rolePrefix("ROLE_").passwordEncoder(passwordEncoder);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors().and().csrf().disable().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http.authorizeRequests().antMatchers(HttpMethod.POST, "/api/signin").permitAll();
        http.authorizeRequests().antMatchers(HttpMethod.GET, "/api/cities").permitAll();
//        http.authorizeRequests().antMatchers(HttpMethod.GET, "/api/category/{id}").permitAll();
//        http.authorizeRequests().antMatchers(HttpMethod.POST, "/api/order").hasAuthority("ROLE_USER");
//        http.authorizeRequests().antMatchers(HttpMethod.GET, "/api/orders").hasAuthority("ROLE_ADMIN");
//        http.authorizeRequests().antMatchers(HttpMethod.GET, "/api/orderItems/{orderId}").hasAuthority("ROLE_ADMIN");
//        http.authorizeRequests().antMatchers(HttpMethod.GET, "/api/order/{orderId}").hasAuthority("ROLE_ADMIN");
//        http.authorizeRequests().antMatchers(HttpMethod.GET, "/api/trainings").permitAll();
//        http.authorizeRequests().antMatchers(HttpMethod.POST, "/api/trainings").hasAuthority("ROLE_ADMIN");
//        http.authorizeRequests().antMatchers(HttpMethod.PUT, "/api/training/{id}").hasAuthority("ROLE_ADMIN");
//        http.authorizeRequests().antMatchers(HttpMethod.DELETE, "/api/trainings/{id}").hasAuthority("ROLE_ADMIN");
//        http.authorizeRequests().antMatchers(HttpMethod.GET, "/api/training/{id}").permitAll();
//        http.authorizeRequests().antMatchers(HttpMethod.GET, "/api/categorie/{id}/trainings").permitAll();
//        http.authorizeRequests().antMatchers(HttpMethod.GET, "/api/trainingImage/{id}").permitAll();
//        http.authorizeRequests().anyRequest().authenticated();
//        http.addFilter(new JwtAuthenticationFilter(authenticationManagerBean()))
//                .addFilterBefore(new JwtAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class);
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
}
