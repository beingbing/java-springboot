package be.springboot.pp.springmvc;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/*
* @Controller and @ResponseBody need to be used together for an HTTP response to an HTTP request,
* or we can use @RestController
* */

//@Controller
@RestController
@RequestMapping("/simple")
public class SimpleController {

    @GetMapping
//    @ResponseBody: signals to send response in HTTP response body
    public String hello() {
        System.out.println("Received request");
        return "hello";
    }

}
