package be.springboot.pp.authentication.filters;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class SuccessfulAuthTrackingFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("SuccessfulAuthTrackingFilter: process request " + servletRequest);
        String header = ((HttpServletRequest) servletRequest).getHeader("Authorization");
        byte[] base64Token = header.substring(6).getBytes(StandardCharsets.UTF_8);
        byte[] decoded = Base64.getDecoder().decode(base64Token);
        String token = new String(decoded, StandardCharsets.UTF_8);
        System.out.println("SuccessfulAuthTrackingFilter: token " + token);
        /*
        * some biz logic to track successful authentication
        * */
        filterChain.doFilter(servletRequest, servletResponse);
    }
}
