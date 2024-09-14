package be.springboot.pp.authentication.filters;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

public class LoginAuthFilter extends OncePerRequestFilter {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        System.out.println("LoginAuthFilter: process request " + request);
        String userName = request.getHeader("username");
        String password = request.getHeader("password");
//        Authentication authentication = new LoginAuthentication(userName, password);
//        Authentication auth = authenticationManager.authenticate(authentication);
//        SecurityContextHolder.getContext().setAuthentication(auth);
        filterChain.doFilter(request, response);
        response.setHeader("Authorization", "abc123456789");
    }

    /*
    * If a request is not coming at below mentioned path then the filter should not filter it.
    * */
    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        return !request.getServletPath().equals("/login");
    }
}
