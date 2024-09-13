package be.springboot.pp.authentication.configs;

import be.springboot.pp.authentication.providers.SampleAuthenticationProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Configuration
public class AuthConfig {

    @Autowired
    private DataSource dataSource;

    @Bean
    public UserDetailsService userDetailsService() {
        List<UserDetails> userDetails = new ArrayList<>();
//        userDetails.add(new User("samar", "samar-taj", Collections.singletonList((GrantedAuthority)() -> "read")));
//        userDetails.add(User.withUsername("Maheen").password("maheen-samar").authorities("read", "write").build());
//        userDetails.add(User.withUsername("rubab").password("rubab-samar").roles("ADMIN").build());
        JdbcUserDetailsManager manager = new JdbcUserDetailsManager(dataSource); // it will store user authentication details in DB
//        manager.createUser(userDetails.get(0));
//        manager.createUser(userDetails.get(1));
//        manager.createUser(userDetails.getFirst());
        return manager;
//        return new InMemoryUserDetailsManager();
    }
    /*
    * here, when we are integrating DB access in spring-security via JDBC, we have to create tables for users and authorities.
    * but what if, those table names / column names are different from what spring-security expects to be ?
    *
    * In that case we need to provide insert queries using setCreateUserSql() on manager object created above.
    * */

    /*
    * queries to create tables for JDBC manager to use -
    * CREATE TABLE users (
    *   username varchar(50) PRIMARY KEY,
    *   password varchar(100),
    *   enabled boolean
    * );
    *
    * CREATE TABLE authorities (
    *   username varchar(50),
    *   authority varchar(50),
    *   CONSTRAINT fk_authorities_users FOREIGN KEY (username) REFERENCES users(username)
    * );
    * */

    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }

    /*
    * After going through tomcat filters, request goes through security-filters from which it is handover to dispatcher servlet.
    * So, this is the place where we configure security filter chain.
    *
    * But these filters come into play only when request is authenticated by authentication-filter
    * */
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http, SampleAuthenticationProvider sampleAuthenticationProvider) throws Exception {
        return http
                .authorizeHttpRequests((authorize) -> authorize
                        .anyRequest().authenticated()) // instead of authenticated()/hasAnyAuthority("write", "read") if permitAll() is used then all requests will pass through
                .httpBasic(Customizer.withDefaults())
                .authenticationProvider(sampleAuthenticationProvider)
                .build();
//        return http
//                .authorizeHttpRequests((authorize) -> authorize
//                        .requestMatchers(HttpMethod.GET, "/simple").hasAuthority("read")
//                        .requestMatchers(HttpMethod.POST, "/auth").permitAll())
//                .httpBasic(Customizer.withDefaults())
//                .authenticationProvider(sampleAuthenticationProvider)
//                .build();
    }
}
