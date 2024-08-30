package be.springboot.pp.springmvc.dispatcherservlet;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerExecutionChain;
import org.springframework.web.servlet.HandlerMapping;

@Order(0) // it will be fist handler mapping in the list of handler-mappings
@Component
public class CustomHandlerMapping implements HandlerMapping {

    @Override
    public HandlerExecutionChain getHandler(HttpServletRequest request) throws Exception {
        if (request.getRequestURI().contains("/custom")) {
            if (request.getMethod().equals("GET")) {
                return new HandlerExecutionChain(new SecondHandler());
            }
            return new HandlerExecutionChain(new CustomHandler());
        }

        return null;
    }
}
