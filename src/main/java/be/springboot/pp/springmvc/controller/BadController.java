package be.springboot.pp.springmvc.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

public class BadController implements Controller {

    // http://localhost:8080/add?a=10&b=20
    @Override
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
        int sum = Integer.parseInt(request.getParameter("a")) + Integer.parseInt(request.getParameter("b"));
        response.getWriter().write("{ sum: " + sum + " } ");
        return null;
    }
}
