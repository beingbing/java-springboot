package be.springboot.pp.springmvc.controller;

import be.springboot.pp.springmvc.dto.ExamResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/*
* For cases, where you add @Controller instead of @RestController look at `ViewReturningController`
* to understand how Spring-MVC works.
* */

@RestController
@RequestMapping("/simple")
public class SimpleController {

    @GetMapping
    public String hello() {
        System.out.println("Received request");
        return "hello";
    }

    // http://localhost:8080/simple/greet?name=samar&say=goodbye
    @RequestMapping(method = RequestMethod.GET, value = "/greet")
    public String greetWithParam(
            @RequestParam(value = "name", defaultValue = "World") String name) {
        System.out.println("Received request with param 'name' " + name);
        return String.format("Hello %s!", name);
    }

    // http://localhost:8080/simple/greet/samar
    @RequestMapping(method = RequestMethod.GET, value = "/greet/{name}")
    public String greetWithPath(
            @PathVariable("name") String name) {
        System.out.println("Received request with path variable 'name' " + name);
        return String.format("Good Bye %s!", name);
    }

    // http://localhost:8080/simple/result
    @RequestMapping(method = RequestMethod.GET, value = "result", produces = "application/xml") // lets experiment, produces = "application/json")
    public ExamResult getExamResult() {
        System.out.println("Received result request");
        return new ExamResult(70, 80, 90);
    }

//    @NotGoodPractice:1
    // curl --location 'http://localhost:8080/simple/result/examine?physics=70&chemistry=65&maths=80'
    @RequestMapping(method = RequestMethod.GET, value = "result/examine")
    public String examineResult(ExamResult examResult) {
        System.out.println("Received examine result request with " + examResult);
        if ((double)examResult.getTotal() /examResult.getMaxScore() >= 0.7) {
            return "passed";
        }
        return "failed";
    }

}
