package be.springboot.test;

import org.springframework.stereotype.Component;

@Component
public class MongoDBConConfigs {

    /*
    *
    * Problem 1: How to scan classes and create beans for them when they are not
    * in the same package in which ApplicationContext was created.
    *
    * Creating this class to test how to create beans of classes which are not in
    * the same package as the one in which main() is initializing spring-boot.
    *
    * Component scanning of Spring by default takes place in the same package in
    * which ApplicationContext is loaded.
    *
    * Check externalconfigs package for seeing how the bean of this class is made.
    * */

}
