package be.springboot.pp.springmvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/view")
public class ViewReturningController {

    @GetMapping
    public String render() {
        System.out.println("Received rendering request");
        return "samar";
    }

}
