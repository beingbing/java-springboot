package be.springboot.pp;

import be.springboot.pp.ApplicationConfigs.JwtConfigs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.servlet.HandlerExceptionResolver;

/*
* TODO: Project Notes -
* - If you are going to fail, then fail fast, why to fail slow.
* - Now you know AOP, learn to implement it as well.
* - objectMapper.convertValue() This method is generally used to convert between objects, not for deserializing JSON strings.
* - Front Controller Design Pattern
* */

// apart from application-local.properties, now test.properties will be considered as well
// my classpath is test.properties
@PropertySource("classpath:test.properties") // please read configurations from this file as well
@SpringBootApplication
public class Application implements ApplicationRunner {

	@Value("${spring.application.name}")
	private String applicationName;

	@Value("${custom.writer:defaultValueIfNoValueIsFoundInCliAndPropertiesFile}")
	private String writer;

	@Value("${to.test.this:testPropertiesFileIsNotInClasspath}")
	private String testValue;

	@Autowired
	private JwtConfigs jwtConfigs;

	public static void main(String[] args) {
		ApplicationContext ctx = SpringApplication.run(Application.class, args);
		System.out.println("Hello Samar !!");

		// printing all beans
//		 for (String bean : ctx.getBeanDefinitionNames()) {
//			System.out.println(bean);

			// to know all the beans initialized by spring under `HandlerExceptionResolver`
			// Object obj = ctx.getBean(bean);
			// if (obj instanceof HandlerExceptionResolver) {
			// 	System.out.println(((HandlerExceptionResolver) obj).getClass().getName());
			// }
//		 }
	}

	@Override
	public void run(ApplicationArguments args) throws Exception {
		System.out.println("Application: run: args: " + writer + " " + applicationName + " test value: " + testValue);
		System.out.println("-=-==-=-=-=-=--==-==-=--=-=-=-=-=-_+_+_++_+_+_+_+_-=--==-=--=-=-=--=-=-=-=--=-=--=-==-");
		System.out.println("JwtConfigs: secret: " + jwtConfigs.getSecret() + " expiration: " + jwtConfigs.getExpiration());
	}
}
