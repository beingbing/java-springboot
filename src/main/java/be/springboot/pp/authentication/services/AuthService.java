package be.springboot.pp.authentication.services;

import be.springboot.pp.authentication.utils.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class AuthService {

    @Autowired
    private JwtUtils jwtUtils;

//    @EventListener(ContextRefreshedEvent.class)
    public void context() {
        System.out.println("AuthService: context started");

        String token = jwtUtils.generateToken("samar.shaikh@bharatpe.com");
        System.out.println("AuthService: token generated: " + token);

        System.out.println("AuthService: context ended");
    }
}
