package be.springboot.pp.authentication.providers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class SampleAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    @Qualifier("inDbUserDetailsService")
    private UserDetailsService userDetailsService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        System.out.println("SampleAuthenticationProvider: authenticate: " + authentication.getPrincipal() + " " + authentication.getCredentials());

        String userName = authentication.getName();
        String password = null;
        UserDetails userDetails = null;

        try {
            userDetails = userDetailsService.loadUserByUsername(userName);
//            userDetails = userDetailsService.loadUserByUsername((String) authentication.getPrincipal());
            password = (String) authentication.getCredentials(); // sent by client
            // internalPwd = userDetails.getPassword(); // stored by us

            if (!passwordEncoder.matches(userDetails.getPassword(), password)) {
                throw new AuthenticationCredentialsNotFoundException("Invalid credentials");
            }
        } catch (Exception e) {
            throw new RuntimeException("Exception thrown while validating credentials");
        }

        return new UsernamePasswordAuthenticationToken(userName, password, userDetails.getAuthorities());
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
    }
}
