package be.springboot.pp.springmvc.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/*
* @Controller and @ResponseBody need to be used together for an HTTP response to an HTTP request,
* or we can use @RestController.
*
* @Controller handed over request made on a particular path to process it and return a result.
* Due to Spring-MVC, A controller looks out for a View, we need to explicitly
* specify that we want an HTTP Response instead.
*
* For cases, where you add @Controller instead of @RestController look at `ViewReturningController`
* to understand how Spring-MVC works.
* */

//@Controller (MVC controller)
@RestController // (REST controller)
@RequestMapping("/simple")
public class SimpleController {

    @GetMapping
//    @ResponseBody: signals to send response in HTTP response body
//    otherwise response goes as an HTML template
    public String hello() {
        System.out.println("Received request");
        return "hello";
//      @Controller will look for hello.html in resources folder by default
//      @RestController/@ResponseBody will treat it as a result to directly include in HTTP response
    }

}
