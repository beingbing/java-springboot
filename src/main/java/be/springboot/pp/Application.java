package be.springboot.pp;

import be.springboot.pp.ApplicationConfigs.JwtConfigs;
import be.springboot.pp.searchtypeahead.entities.QueryFrequency;
import be.springboot.pp.searchtypeahead.repositories.QueryFrequencyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.PropertySource;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.servlet.HandlerExceptionResolver;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

/*
* TODO: Project Notes -
* - If you are going to fail, then fail fast, why to fail slow.
* - Now you know AOP, learn to implement it as well.
* - objectMapper.convertValue() This method is generally used to convert between objects, not for deserializing JSON strings.
* - Front Controller Design Pattern. example - DispatcherServlet
* - Functional-interface and default methods
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

	@Autowired
	private QueryFrequencyRepository queryFrequencyRepository;

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

//			 if (obj instanceof UserDetailsService) {
//				 System.out.println("the bean is: " + bean);
//				 System.out.println("and the bean is: " + obj);
//			 }
//		 }
	}

	@Override
	public void run(ApplicationArguments args) throws Exception {
		System.out.println("Application: run: args: " + writer + " " + applicationName + " test value: " + testValue);
		System.out.println("-=-==-=-=-=-=--==-==-=--=-=-=-=-=-_+_+_++_+_+_+_+_-=--==-=--=-=-=--=-=-=-=--=-=--=-==-");
		System.out.println("JwtConfigs: secret: " + jwtConfigs.getSecret() + " expiration: " + jwtConfigs.getExpiration());
//		populateQueryFrequencyTable();
	}

	private void populateQueryFrequencyTable() {
		List<QueryFrequency> data = generateData();
		for (QueryFrequency queryFrequency : data)
			queryFrequencyRepository.save(queryFrequency);
	}

	private List<QueryFrequency> generateData() {
		List<QueryFrequency> data = new ArrayList<>();
		String alphabets = "samr";
		Map<String, Integer> freqMap = new HashMap<>();
		Random r = new Random();
		for (int i = 0; i < 100000; i++) {
			int length = r.nextInt(1, 7);
			StringBuilder sb = new StringBuilder();
			for (int j = 0; j < length; j++) {
				sb.append(alphabets.charAt(r.nextInt(alphabets.length())));
			}
			freqMap.put(sb.toString(), freqMap.getOrDefault(sb.toString(), 0) + 1);
		}

		for (Map.Entry<String, Integer> entry : freqMap.entrySet())
			data.add(new QueryFrequency(entry.getKey(), (long) entry.getValue()));
		return data;
	}
}
