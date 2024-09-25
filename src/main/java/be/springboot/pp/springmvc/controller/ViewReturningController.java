package be.springboot.pp.springmvc.controller;

import be.springboot.pp.springmvc.returnvaluehandler.SampleReturnType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/*
* returning ResponseEntity is equivalent to your desire to write to the response.
* When we use @RestController, ResponseBodyEmitterReturnValueHandler is responsible for checking
* response type and process if it is an instance of type ResponseEntity.
* */

/*
* As we have, `RequestMappingHandlerAdapter` class in spring-mvc which maintains a list of
* `HandlerMethodReturnValueHandler`, loops over each one of them and checks if any of them
* can handle current `HandlerMethod` return type.
* Similarly, it also maintains a list of `HandlerMethodArgumentResolver` interface and loops
* over `HandlerMethod` parameters to check if any of them can handle the parameters.
*
* There are so many such components like these inside `RequestMappingHandlerAdapter` to
* extract required components from HTTP requests and send them to `HandlerMethod` and
* then build an HTTP response with such components as they  are defined in `HandlerMethod`
* */

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
