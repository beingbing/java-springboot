package be.springboot.pp.ApplicationConfigs;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component
@ConfigurationProperties(prefix = "jwt") // it will look for all the keys with "jwt" as prefix
public class JwtConfigs {

    private String secret;

    private String expiration;
}
/*
* Setters are required as values are not set using reflection, they are done using setters after creating an instance.
*
* It is kept this way so that setters can have data integrity checks
* and no SQL injection to disrupt the application can be done.
* */
