package be.springboot.pp.authentication.filters;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;

import java.io.IOException;

public class RequestForwardingValidationFilter implements Filter {

    private static final String LB_SECRET = "LB_SECRET"; // load balancer secret, verifying that request came from our public facing load balancer

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest)servletRequest;
        HttpServletResponse response = (HttpServletResponse)servletResponse;

        if (request.getHeader(LB_SECRET) == null || !request.getHeader(LB_SECRET).equals("LB_SECRET_VALUE")) {
            response.setStatus(HttpStatus.BAD_GATEWAY.value());
            response.getWriter().write("We know that you are spam");
            response.sendError(HttpServletResponse.SC_FORBIDDEN);
            return;
        }

        filterChain.doFilter(request, response);
    }
}
