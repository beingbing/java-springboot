package be.springboot.pp.springmvc.config;

import be.springboot.pp.springmvc.controller.BadController;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerMapping;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.handler.SimpleUrlHandlerMapping;
import org.springframework.web.servlet.mvc.Controller;

import java.util.HashMap;
import java.util.Map;

/*
 * Whenever we wish to have a Spring registered Bean for a class which is not by default
 * provided to you by the framework, but is present in one of the JAR files downloaded and
 * provided by Maven, then you can create a Configuration class and create a Bean for that
 * class.
 * */

@Configuration
@EnableWebMvc // it will signal spring-mvc to review this class, as it will not go looking for custom beans
public class UrlConfig {

    // once spring-mvc receives the signal, it will review the class and when it finds a HandlerMapping
    // then spring-mvc will inject this mapping in the list of HandlerMapping of DispatcherServlet
    @Bean
    public HandlerMapping createHandlerMapping() {
        SimpleUrlHandlerMapping handlerMapping = new SimpleUrlHandlerMapping();
        Map<String, Controller> urlMap = new HashMap<>();
        urlMap.put("/add", new BadController());
        handlerMapping.setUrlMap(urlMap);
        return handlerMapping;
    }
}
