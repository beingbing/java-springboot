package be.springboot.pp.authentication.configs;

import be.springboot.pp.authentication.filters.LoginAuthFilter;
import be.springboot.pp.authentication.filters.RequestForwardingValidationFilter;
import be.springboot.pp.authentication.filters.SuccessfulAuthTrackingFilter;
import be.springboot.pp.authentication.providers.SampleAuthenticationProvider;
import jakarta.servlet.Filter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

@Configuration
public class AuthConfig {

    @Autowired
    private DataSource dataSource;

    @Bean
    public UserDetailsService inMemoryCustomUserDetailsService() {
        List<UserDetails> userDetails = new ArrayList<>(); // it will store user authentication details in RAM
        userDetails.add(User.withUsername("samar").password("samar-taj").authorities("read").build());
        userDetails.add(User.withUsername("Maheen").password("maheen-samar").authorities("read", "write").build());
        userDetails.add(User.withUsername("rubab").password("rubab-samar").roles("ADMIN").build());
        return new InMemoryUserDetailsManager(userDetails);
    }

    @Bean
    public PasswordEncoder customPasswordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }

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
    public UserDetailsService inDbUserDetailsService() {
        JdbcUserDetailsManager manager = new JdbcUserDetailsManager(dataSource); // it will store user authentication details in DB
//        UserDetailsUtils.createUserDetailsInDB(manager); // it will store user authentication details in DB
        return manager;
    }

    // to customize an authentication-provider to be implemented on specific requests.
    // we will do that by creating a custom bean of `SecurityFilterChain`
   @Bean
    public SecurityFilterChain SampleAuthenticationFilterChain(HttpSecurity http, SampleAuthenticationProvider sampleAuthenticationProvider) throws Exception {
        /*
        * write now, it is set for all request to be authenticated via sampleAuthenticationProvider hence there is not
        * much significant  difference. But via this we can restrict some requests to be authenticated.
        * Or allow only specific endpoint to be authenticated via sampleAuthenticationProvider.
        * */
        return http
                .authorizeHttpRequests((authorize) -> authorize.anyRequest().authenticated())
                .httpBasic(Customizer.withDefaults())
                .authenticationProvider(sampleAuthenticationProvider)
                .build();
        // instead of authenticated() we have, permitAll(), denyAll(), hasAuthority(), hasAnyAuthority(String...) and other options as well.
        // here authenticated() implements authentication whereas permitAll() will permit all of them
        // and denyAll() will not allow any of the requests.
        // hasAuthority(String) allows a request authenticator with mentioned authority to be able to reach to the controller
        // hasAuthority(String) is an authorization filtration check, it will go through granted authorities once authentication
        // is done and check for the mentioned authority.
    }

    // in above customization, we can kept single filter in filter-chain and customized on which routes it will look for which
    // authentication-provider. But now, we will actually see customization of both filters and authentication-provider in filter-chain
    // @Bean
    public SecurityFilterChain filterChain(HttpSecurity http, SampleAuthenticationProvider sampleAuthenticationProvider) throws Exception {
        SecurityFilterChain sfc = http
                .addFilterBefore(new RequestForwardingValidationFilter(), BasicAuthenticationFilter.class) // this filter will be added before BasicAuthenticationFilter
                .addFilterAt(new LoginAuthFilter(), BasicAuthenticationFilter.class)
                .addFilterAfter(new SuccessfulAuthTrackingFilter(), BasicAuthenticationFilter.class)
                .authorizeHttpRequests((authorize) -> authorize
                        .requestMatchers(HttpMethod.GET, "/simple").hasAuthority("read")
                        .requestMatchers(HttpMethod.GET, "/greet").hasAnyAuthority("read", "write")
                        .requestMatchers(HttpMethod.POST, "/auth").permitAll()
                        .requestMatchers(HttpMethod.POST, "/inventory").hasRole("ADMIN") // roles internally get converted into granted-authority after getting prefixed with "ROLE_"
                ).httpBasic(Customizer.withDefaults())
                .authenticationProvider(sampleAuthenticationProvider)
                .build();

        for (Filter filter : sfc.getFilters()) {
            System.out.println("filter: " + filter.getClass().getName());
        }

        return sfc;
    }
}
