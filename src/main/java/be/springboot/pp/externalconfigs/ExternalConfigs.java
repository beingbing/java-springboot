package be.springboot.pp.externalconfigs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@ComponentScan(basePackages = "be.springboot.test") // Solves Problem 1.
@Configuration // Solves Problem 2 and 3.
public class ExternalConfigs {

    @Autowired
    private CustomRestClient customRestClient;

    /*
    * Problem 2: Our project needs a third-party library. Inside that, we have classes
    * annotated for bean creation, but beans do not get created. Why ?
    *
    * Those classes are not present in package in which component scanning is takig place
    * hence their beans are also not getting created.
    *
    * Problem 3: A variation of Problem 2. When Our project needds a third-party library
    * classes but they are not annotated for bean creation. So how to create their beans ?
    *
    * Both of them have the same solution, which is @Configuration/@Bean classes.
    * */

    @Bean
    public RestTemplate CustomRestTemplate() {
        return customRestClient.getCustomRestTemplate();
    }
}
