package fr.fms.apihotel.security;

import fr.fms.apihotel.dao.UsersRepository;
import fr.fms.apihotel.entities.Users;
import fr.fms.apihotel.service.IService;
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
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.util.List;
import java.util.Optional;

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
        http.authorizeRequests().antMatchers(HttpMethod.GET, "/api/city/{id}").permitAll();
        http.authorizeRequests().antMatchers(HttpMethod.GET, "/api/hotels").permitAll();
        http.authorizeRequests().antMatchers(HttpMethod.POST, "/api/hotels").hasAuthority("ROLE_ADMIN");
        http.authorizeRequests().antMatchers(HttpMethod.PUT, "/api/hotel/{id}").hasAuthority("ROLE_ADMIN");
        http.authorizeRequests().antMatchers(HttpMethod.DELETE, "/api/hotels/{id}").hasAuthority("ROLE_ADMIN");
        http.authorizeRequests().antMatchers(HttpMethod.GET, "/api/hotel/{id}").permitAll();
        http.authorizeRequests().antMatchers(HttpMethod.GET, "/api/city/{id}/trainings").permitAll();
        http.authorizeRequests().antMatchers(HttpMethod.GET, "/api/hotelImage/{id}").permitAll();
        http.authorizeRequests().anyRequest().authenticated();
        http.addFilter(new JwtAuthenticationFilter(authenticationManagerBean()))
                .addFilterBefore(new JwtAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class);
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

    @Service
    public static class ImplUserService implements IService<Users> {

        @Autowired
        UsersRepository usersRepository;

        @Autowired
        SecurityConfig security;

        @Autowired
        public JwtUtils jwtUtils;

        @Override
        public List<Users> getAll() {
            return usersRepository.findAll();
        }

        @Override
        public Optional<Users> getOneById(long id) {
            return usersRepository.findById(id);
        }

        @Override
        public Users save(Users obj) {
            return usersRepository.save(obj);
        }

        @Override
        public void delete(long id) {

        }

        public Users getUser(String email) {
            return usersRepository.findByEmail(email);
        }

        public String encodePassword(String mdp) {
            return security.encodePassword(mdp);
        }

        public Users findByEmail(String email){
            return usersRepository.findByEmail(email);
        }

    }
}
