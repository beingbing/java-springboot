package be.springboot.pp.springmvc.controller;

import be.springboot.pp.springmvc.returnvaluehandler.SampleReturnType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller // (MVC controller)
@RequestMapping("/view")
public class ViewReturningController {

    @GetMapping
    public String render() {
        System.out.println("Received rendering request");
        return "samar";
    }

    @GetMapping("/ping")
    public SampleReturnType ping() {
        System.out.println("Sample return type rendering request");
        return new SampleReturnType("samar is pinging");
    }

}
