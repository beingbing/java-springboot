package be.springboot.pp.externalconfigs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@ComponentScan(basePackages = "be.springboot.test")
@Configuration
public class ExternalConfigs {

    @Autowired
    private CustomRestClient customRestClient;

    @Bean
    public RestTemplate CustomRestTemplate() {
        return customRestClient.getCustomRestTemplate();
    }
}
