package be.springboot.pp.springmvc.controller;

import be.springboot.pp.springmvc.returnvaluehandler.SampleReturnType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/*
* There must be some component which reviews the response of `HandlerMethod` and if
* its a string then looks for a view file with name of the string and returns it.
*
* Q. But what if return type is not a String and a random custom object ?
* A. Then we need to define how to handle it by writing custom handler.
*
* `HandlerMethodReturnValueHandler` is the interface responsible for handling return types
* of controller methods. One of its implementation `ViewNameMethodReturnValueHandler` is
* responsible for looking out for a view file if returned type is a String.
*
* It has two methods -
* `boolean supportsReturnType(MethodParameter returnType)` is used to check if a return type
* is supported by an implementation, if yes, then we have `handleReturnValue()` which is used
* to then handle the supported return type.
*
* `MethodParameter` is used to get the return type of the `HandlerMethod`. It might be
* String.class, Double.class, etc.
*
* */

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

@Controller
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
