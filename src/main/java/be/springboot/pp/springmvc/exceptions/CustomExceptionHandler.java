package be.springboot.pp.springmvc.exceptions;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;

@Component // alternative is to add it manually in the list of exception resolvers using `WebMvcConfigurer`
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
