package be.springboot.pp.springmvc.filters;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Order(1) // to decide which filter should be executed first
@Component // check if it is needed
public class SampleFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("SampleFilter: process request " + servletRequest);

        filterChain.doFilter(servletRequest, servletResponse);

        System.out.println("SampleFilter: processing response " + servletRequest);
    }
}
