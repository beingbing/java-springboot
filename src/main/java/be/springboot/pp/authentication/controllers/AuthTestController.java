package be.springboot.pp.authentication.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthTestController {

    @GetMapping
    public String hello() {
        System.out.println("Received request");
        return "hello";
    }
}
