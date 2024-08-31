package be.springboot.pp.springmvc.exceptions;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;

/*
* `HandlerExceptionResolverComposite` is composed of other resolvers and this one is responsible for resolving exceptions
* Spring initializes it as a Bean, and during runtime exception-resolvers list is populated.
* */

@Order(0) // to give it preference in precedence order
@Component
public class CustomExceptionHandler implements HandlerExceptionResolver {
    @Override
    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        if (ex instanceof CustomException) {
            try {
                response.getWriter().write("{\"errorType\": \"" + ex.getClass().getSimpleName() + "\", \"message\": \"" + ex.getMessage() + "\"}");
                response.setStatus(404);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            return new ModelAndView(); // if null is sent then this class won't be considered as a candidate to handle CustomException
        }

        return null;
    }
}
