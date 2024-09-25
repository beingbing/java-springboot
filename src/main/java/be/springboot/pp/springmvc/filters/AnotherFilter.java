package be.springboot.pp.springmvc.filters;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Order(2)
@Component
public class AnotherFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("AnotherFilter: process request " + servletRequest);

        filterChain.doFilter(servletRequest, servletResponse);

        System.out.println("AnotherFilter: processing response " + servletRequest);
    }
}
