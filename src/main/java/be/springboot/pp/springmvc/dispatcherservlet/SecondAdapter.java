package be.springboot.pp.springmvc.dispatcherservlet;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerAdapter;
import org.springframework.web.servlet.ModelAndView;

@Component
public class SecondAdapter implements HandlerAdapter {
    @Override
    public boolean supports(Object handler) {
        return handler instanceof SecondHandler;
    }

    @Override
    public ModelAndView handle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("SecondHandler: handle " + request);
        ((SecondHandler)handler).handleRequest(request, response);
        return null;
    }

    /**
     * @param request
     * @param handler
     * @deprecated
     */
    @Override
    public long getLastModified(HttpServletRequest request, Object handler) {
        return 0;
    }
}
