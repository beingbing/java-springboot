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

    /*
    * controllers can decide authority level of a request using security-context created by
    * authentication-filter when request was successfully authenticated.
    *
    * But at a given time, due to multi-threading there will be multiple security-context object
    * residing in the SecurityContextHolder, then how below code can decide which security-context
    * was associated with the given request ?
    *
    * Answer to this is, every Thread has a ThreadLocal. SecurityContextHolder uses Strategy design
    * pattern to define a holder-strategy for every request, as every request is assigned to a Thread.
    * and Threads have unique id, hence security-context is saved against a thread id.
    * Hence, we can decide which security-context was associated with the given request.
    * */
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
