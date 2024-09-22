package be.springboot.pp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.web.servlet.HandlerExceptionResolver;

/*
* TODO: Project Notes -
* - If you are going to fail, then fail fast, why to fail slow.
* - Now you know AOP, learn to implement it as well.
* - objectMapper.convertValue() This method is generally used to convert between objects, not for deserializing JSON strings.
* */

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		ApplicationContext ctx = SpringApplication.run(Application.class, args);
		System.out.println("Hello Samar !!");

		// printing all beans
		 for (String bean : ctx.getBeanDefinitionNames()) {
			System.out.println(bean);

			// to know all the beans initialized by spring under `HandlerExceptionResolver`
			// Object obj = ctx.getBean(bean);
			// if (obj instanceof HandlerExceptionResolver) {
			// 	System.out.println(((HandlerExceptionResolver) obj).getClass().getName());
			// }
		 }
	}

}
