package be.springboot.pp.springmvc.dispatcherservlet;

import be.springboot.pp.springmvc.exceptions.CustomException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class SecondHandler {

    public void handleRequest(HttpServletRequest request, HttpServletResponse response) throws IOException, CustomException {
        if (request.getParameter("token") == null)
            throw new CustomException("please provide a token");

        response.getWriter().write("i am coming from SecondHandler");
    }

}
