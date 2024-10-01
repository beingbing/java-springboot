package be.springboot.pp.authentication.controllers;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/inventory")
public class InventoryController {

    @RequestMapping(method = RequestMethod.GET, value = "/list")
    public String list() {
        SecurityContext context = SecurityContextHolder.getContext();
        System.out.println("User: " + context.getAuthentication().getPrincipal());
        for (GrantedAuthority authority : context.getAuthentication().getAuthorities()) {
            System.out.println("Authority: " + authority.getAuthority());
        }
        System.out.println("Authenticated: " + context.getAuthentication().isAuthenticated());
        System.out.println("Credentials: " + context.getAuthentication().getCredentials());
        System.out.println("Details: " + context.getAuthentication().getDetails()); // `AuthenticationDetails`
        return "list";
    }
}
