# Spring
It is a framework for building Java Applications, responsible for managing components, their dependencies,
and how they interact. It provides a wide range of tools and abstractions. The foundation feature
of the Spring framework is called Spring Core. Out of the box, it provides IoC container and DI
implementation, which is responsible for managing object lifecycles and resolving dependencies
between them.

Spring Core abstracts the creation and management of objects (beans), providing mechanisms for
configuration and wiring components together. It enables developers to focus on business logic rather
than on boilerplate code for object management.

## Why Spring ?
Without using `new` keyword, Spring gives you objects on which instances of your class depends. If
you need to create instance of class `A` which depends on objects of class `B`, `C` and `D` then
you normally does -
```
A a = new A(new B(), new C(), new D());
```
Spring does the exact thing for you but without using the `new` keyword. Because, if code delays
`new` keyword or doesn't involve it then code is extensible and easy to test. Spring does this
by Dependency Injection of objects which it maintains in IoC container. So, Spring gets rid of
`new` keyword completely, and make your code ideal for extensible hence ideal for writing a
microservice in it, just by using IoC containeer and DI.

### Q. why to use IoC container, why can't a manager class does the same thing ?
The responsibility of object creation should not be given to a managing class, because to make all
types of objects and return them, class needs to take input of request to create those object.
Hence, manager class will be taking input a type which will represent what type of object it need
to make. So, every time we add a new class in our code base we need to modify manager-class to be
able to make objects of newly created class as well, when requested. This is a bad design. Also,
if anyone of those classes whose objects managing-class make do not compile then none of the code
will work. Which is also a big drawback. So, every class should be given readymade objects to use.
And none of them should be given responsibility of creating them. This is called inversion of
control. No classes have control over creation of objects. Objects are created by a client and
provided to them to work on. And this phenomenon of injecting required objects inside constructor of
a class by caller/client is called Constructor based Dependency Injection.

## Inversion of Control (IoC)
Its a design principle where the control of object creation, configuration and lifecycle management
is handled by container/framework, rather than being managed within the application code. Spring
provides an IoC container, which uses Dependency Injection (DI) to resolve and inject dependencies
between objects.

### IoC Container
Spring Core provides two main types of IoC containers -
- `BeanFactory`: The simplest container providing basic DI functionality. It lazily loads beans,
meaning that beans are created only when needed.
- `ApplicationContext`: A more advanced container that builds on top of `BeanFactory` with extra
features like event propagation, AOP, declarative mechanisms for creating beans, and support for
internationalization. It eagerly loads singleton beans (creates them at startup).

```java
// an ApplicationContext is created using a Java-based configuration class (AppConfig), and the
// UserService bean is retrieved from the container.
ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);
UserService userService = context.getBean(UserService.class);
```

#### Types of ApplicationContext
- `ClassPathXmlApplicationContext`: loads configuration from XML files on the classpath.
- `FileSystemXmlApplicationContext`: loads configuration from XML files in the file system.
- `AnnotationConfigApplicationContext`: loads configuration from Java-based annotations (since Spring 3.0)

## Dependency Injection
A design pattern where an object's dependencies (other objects it needs to function) are injected
into it by the framework (the IoC container), rather than being created within the class.
To Inject polymorphic type, if more than one objects are present, then spring can resolve the object
by variable-name, or use `@Qualifier` to explicitly tell spring which polymorphic object needs to
be injected. Its types are -
- Constructor based: Dependencies are provided through the constructor. **Most recommended because
it makes the object immutable and enforces dependency injection at the time of object creation.**
by using `Optional<>` on constructor arguments we can make some dependencies optional as well.
```java
public class MyService {
    private final MyRepository repository;

    @Autowired
    public MyService(MyRepository repository) {
        this.repository = repository;
    }
}
```
- setter-based: Dependencies are provided through setter methods (good for optional dependencies)
```java
public class MyService {
    private MyRepository repository;

    @Autowired(required = false)
    public void setRepository(MyRepository repository) {
        this.repository = repository;
    }
}
```
- field-based (via reflection) (not recommended due to reduced testability): Dependencies are directly
injected into fields using @Autowired. It creates hidden dependency, as client initializing object
using contructor will not know about DI done using reflection.
```java
public class MyService {
    @Autowired
    private MyRepository repository;
}
```

## Beans
A bean is any object managed by Spring's IoC container. Beans are instantiated, configured, and
injected by the container based on the definitions provided in the annotation configuration.

### Bean lifecycle
- **Instantiation:** The IoC container creates an instance of the bean.
- **Property Injection:** The container injects dependencies.
- **Custom Initialization:** Custom initialization logic can be applied using `@PostConstruct` or by
  implementing `InitializingBean` or `initMethod` is called (if specified)
- **Bean Usage:** The bean is ready to be used.
- **Custom Destruction:** Custom destruction logic can be added using `@PreDestroy` or by implementing
  `DisposableBean` or `destroyMethod` id called when the bean is no longer needed.

### Bean Scope
The scope defines how many instances of a bean are created by the IoC container. Options are -
- Singleton (default): Only one instance of the bean is created for the entire Spring container.
- Prototype: A new instance is created every time the bean is requested.
- request: A new instance is created for each HTTP request
- session: A new instance is created for each HTTP session
- global-session:

### Bean Configuration
- Annotation-based configuration
```java
@Component
public class MyService {
    @Autowired
    private MyRepository repository;
}
```
- Java-based configuration (can also be used to create bean manually for a third party class which is not in classpath)
(can also be used to create a variant of already existing bean with another name and some customization)
```java
@Configuration
public class AppConfig {
    @Bean
    public MyService myService() {
        return new MyService(myRepository());
    }

    @Bean
    public MyRepository myRepository() {
        return new MyRepository();
    }
}
```
- XML-Based Configuration
```xml
<bean id="myBean" class="com.example.MyBean">
    <property name="myDependency" ref="myDependencyBean" />
</bean>
```

### Bean Autowiring
Spring can automatically resolve and inject beans by their type using autowiring. It is also called
automatic dependency injection. It offers several modes of autowiring:
- **No:** Default mode where no autowiring is performed.
- **ByType:** Matches by the type of bean.
- **ByName:** Matches by the bean's name.
- **Constructor:** Uses constructor injection.

While creating beans, make sure that it is Directed Acyclic Graph (DAG), otherwise Spring won't be able to resolve cyclic dependencies and application will fail. Keep in mind that even if two entites exhibit one-to-one mapping during entity modelling, still it is not necessary to translate that relation during object Modelling. Example, Customer and Cart entites exhibit one-to-one mapping as entity, but it is not necessary that Customer object has a Cart object as well as Cart object has a Customer object. Avoid these situations.

## Spring Profiles
Spring Profiles allow you to define different configurations for different environments (development,
production, etc.). For example, you might want to use different data sources for development and
production. You can activate profiles through configuration files, environment variables, or JVM
arguments.
```java
@Configuration
@Profile("development")
public class DevelopmentConfig {
    @Bean
    public DataSource dataSource() {
        // Return development DataSource
    }
}
```

## Events and Listeners
Spring Core provides a way to publish and listen to events using the `ApplicationEventPublisher`.
It helps in creating loosely coupled components.
- Publishing an event:
```
applicationEventPublisher.publishEvent(new MyCustomEvent(this));
```
- Listening to an event:
```java
@EventListener
public void handleMyCustomEvent(MyCustomEvent event) {
    // Handle event
}
```

## Spring AOP (Aspect-Oriented Programming)
AOP allows handling cross-cutting concerns (like logging, security, transaction management, etc.)
separately from the main business logic. This keeps business code clean and focused. It works by
applying advice (a piece of code) around methods via aspects. It provides -
- **Aspect:** Modularization of a concern that cuts across multiple objects (A module that encapsulates pointcuts and advice)
- **Join Point:** A point in the execution of the program, such as method invocation or exception.
- **Advice:** Defines the action to take by an Aspect at a join point (e.g., `@Before`/`@After`/`@Around` method execution)
- **Pointcut:** Defines the execution points where aspects should be applied. A predicate that matches
join points.

```java
@Aspect
@Component
public class LoggingAspect {
  @Before("execution(* com.example.*.*(..))")
  public void logBeforeMethod(JoinPoint joinPoint) {
    System.out.println("Method called: " + joinPoint.getSignature().getName());
  }
}
```

## Key annotations in Spring
- `@Component("userDefinedName")`: indicates a class is a Spring-managed bean. (putting it on interface is useless as interface can't be instantiated)
- `@Autowired`: for dependency injection into a class
- `@Bean`: used to define a bean within a `@Configuration` class in Java-based configuration.
- `@Configuration`: A specialization of `@Component` indicates that a class contains custom Spring bean definitions.
- `@Service`: A specialization of `@Component` for service-layer beans.
- `@Repository`: A specialization of `@Component` for data access objects (DAOs).
- `@Controller`: A specialization of `@Component` indicating a class as a Spring MVC controller
- `@RestController`: a combination of `@Controller` and `@ResponseBody` for REST APIs
- `@EnableAutoConfiguration`: Automatically configures Spring Boot applications based on the classpath.

# Spring Boot
Spring Boot is a Spring framework module designed to simplify the development, configuration, and
deployment of Java applications. It removes much of the complexity involved in traditional Spring
setups by using `convention over configuration`. Offering opinionated, minimal, default setups that
reduce the need for extensive manual configuration. This allows developers to quickly start projects
while still leveraging the full power of the Spring ecosystem.

## Key Features
- **Auto-configuration:** automatically configures beans based on project dependencies, classpath
and other conditions
- **Embedded Servers:** runs application with embedded server (e.g., Tomcat, Jetty), eliminating the
need for external deployment.
- **Opinionated Defaults:** provides sensible default configurations that can be customized when needed
- **Starter Dependencies:** Instead of adding individual dependencies, Spring Boot provides "starter"
dependencies (like, `spring-boot-starter-web`) that bundles everything you need to get started for
a particular use case.
- **Spring Boot CLI:** Command-line tool to quickly run and test Spring applications.
- **Production-ready Features:** Monitoring, metrics, health checks, and more via Spring Boot Actuator.

## Why Spring Boot
Spring Boot reduces boilerplate code and manual configuration typically required in Spring:
- No need to manually set up application servers.
- Eliminates the need for configuring XML files for beans and dependencies.
- Automatically manages dependencies and third-party library integration.
- Offers production-ready features with minimal effort, enabling quick prototyping and deployment.

In summary, Spring Boot streamlines Spring development by auto-configuring components, embedding
servers, and providing defaults, allowing developers to focus on business logic rather than dealing
with configuration details.

## Spring Boot Auto-Configuration
One of the most powerful features of Spring Boot is its auto-configuration ability. Automatically
configures many components, like databases, web servers, and security, Based on the dependencies in
your project/classpath, Spring Boot automatically configures the necessary parts. If it detects
certain libraries, it will auto configure beans related to those libraries. For example:
- If you add spring-boot-starter-web, Spring Boot will configure:
  - A DispatcherServlet for handling web requests.
  - A Jackson ObjectMapper for JSON serialization/deserialization.
  - An embedded Tomcat server to serve the application.
    You can always override this auto-configuration by defining your own configurations.

## Setting Up a Spring Boot Project
The simplest way to create a Spring Boot project is by using Spring Initializr (https://start.spring.io/).
Here, you can select the dependencies you want and download the pre-configured project. You can also
create the project manually by setting up a pom.xml (for Maven) or build.gradle (for Gradle). Here’s
a basic pom.xml for Maven:
```xml
<parent>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-parent</artifactId>
    <version>3.0.0</version>
</parent>

<dependencies>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-web</artifactId>
    </dependency>
</dependencies>
```
The key part here is the starter dependencies

## Starter Dependencies
Spring Boot simplifies dependency management by providing starter dependencies. These are pre-defined
collections of dependencies that include everything you need for a specific feature. For example:
- **spring-boot-starter-web**: Includes dependencies to build web applications, like Spring MVC,
Jackson (for JSON), and an embedded Tomcat server.
- **spring-boot-starter-data-jpa**: Includes dependencies for working with Spring Data JPA and
Hibernate for database interactions.
- **spring-boot-starter-security**: For adding security to your application with Spring Security.
- **spring-boot-starter-test**: For unit testing and integration testing using JUnit, Mockito, and Spring Test.
```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-web</artifactId>
</dependency>
```
You don’t have to worry about adding individual dependencies like Jackson, Spring MVC, or Tomcat
manually. Spring Boot handles that for you.

## Embedded Web Servers
Spring Boot comes with embedded servers (Tomcat by default) which allow you to run your web
applications without deploying them to an external server.

If you want to switch to Jetty or Undertow, you just include the respective starter:
```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-jetty</artifactId>
</dependency>
```

## Spring Boot Project Structure
A typical Spring Boot project looks like -
```
src/
  main/
    java/
      com/example/demo/
        DemoApplication.java   // Main class
    resources/
      application.properties   // External configuration file
  test/
    java/
      com/example/demo/        // Unit tests and integration tests
pom.xml                        // Maven file for managing dependencies
```
- `DemoApplication.java`: This is the main entry point for the Spring Boot application. It contains
the `main()` method to launch the application.
- `application.properties`: This file holds configuration properties (like database settings, server port, etc.).
- `pom.xml`: A Maven file (if using Maven) that lists all project dependencies.

## Main Application Class
Every Spring Boot application starts with a main class annotated with `@SpringBootApplication`.
This annotation enables:
- **Auto-configuration:** `@EnableAutoConfiguration` Automatically configures Spring components by
scanning the classpath and setting up beans and components automatically. For example, if it detects
`H2`/`MySQL` dependencies, it sets up database connections automatically.
- **Component scanning:** `@ComponentScan` Scans for Spring components like @Component, @Service, @Repository, etc.
- **Spring Boot configuration:** Simplifies setting up Spring Boot features.
```java
@SpringBootApplication
public class MySpringBootApp {
    public static void main(String[] args) {
        SpringApplication.run(MySpringBootApp.class, args); // starts the Spring Boot Application
    }
}
```
When you run this class, Spring Boot starts your application and the embedded server (e.g., Tomcat).

## Spring Boot Configuration
Spring Boot allows you to configure application settings by externalizing configuration into a
properties or YAML file. These properties can be set without modifying the application code.
- application.properties:
```properties
server.port=8081
spring.datasource.url=jdbc:mysql://localhost:3306/mydb
spring.datasource.username=root
spring.datasource.password=pass
```
- application.yml:
```yaml
server:
  port: 8081
spring:
  datasource:
    url: jdbc:mysql://localhost:3306/mydb
    username: root
    password: pass
```
The application.properties or application.yml file is loaded automatically by Spring Boot and
configures your application without requiring boilerplate code. Spring Boot allows you to override
properties with environment variables or command-line arguments, making it highly customizable.

## Spring Boot Profiles
Spring Boot supports profiles to allow you to run different configurations for different environments
(e.g., development, testing, production). You can define different `application.properties` or
`application.yml` files like `application-dev.properties`, `application-prod.properties`.

To activate a profile, you can use:
- command-line arguments
```shell
java -jar myapp.jar --spring.profiles.active=prod
```
- In `application.properties`
```properties
spring.profiles.active=prod
```

## Spring Boot DevTools
Spring Boot DevTools provides utilities to improve the development experience. This includes automatic
restart when files change, live reloading, and configurations for speeding up the development process.
To add DevTools:
```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-devtools</artifactId>
    <scope>runtime</scope>
</dependency>
```

## Spring Boot Actuator
Spring Boot Actuator provides production-ready features to help monitor and manage your application.
It includes features like health checks, metrics, and auditing. To include Actuator:
```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-actuator</artifactId>
</dependency>
```
Some of the common Actuator endpoints are:
- `/actuator/health`: Shows the health of the application.
- `/actuator/metrics`: Shows various metrics like memory usage, HTTP request counts, etc.
- `/actuator/info`: Displays arbitrary application information.
- `/actuator/loggers`: allows you to view and change log levels dynamically.

## Customizing Spring Boot
Even though Spring Boot provides a lot of default configurations, you can still customize it by either:
- Defining custom configurations in your `@Configuration` classes.
- Overriding properties in `application.properties`.
For example, to change the default server port:
```properties
server.port=8081
```
Or programmatically via SpringApplication:
```java
@SpringBootApplication
public class MyApp {
    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(MyApp.class);
        app.setDefaultProperties(Collections.singletonMap("server.port", "8081"));
        app.run(args);
    }
}
```

## Testing in Spring Boot
Spring Boot simplifies testing by including spring-boot-starter-test. This provides:
- **JUnit 5**: For writing unit and integration tests.
- **Spring Test**: To easily load the application context for testing.
- **Mockito**: For creating mocks.
  You can annotate your test classes with `@SpringBootTest` to load the full application context.
```java
@SpringBootTest
class MyApplicationTests {

    @Test
    void contextLoads() {
    }
}
```
For specific slices of the application, Spring Boot provides specialized annotations like:
- `@WebMvcTest`: To test only the web layer.
- `@DataJpaTest`: To test only the JPA layer.

## Spring Boot Security
Spring Boot also supports Spring Security, making it easy to add authentication and authorization
to your application. Add the `spring-boot-starter-security` dependency:
```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-security</artifactId>
</dependency>
```
This will secure all endpoints by default, requiring authentication for every request.

You can configure custom security rules by defining a `SecurityConfig` class and extending `WebSecurityConfigurerAdapter`:
```java
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .authorizeRequests()
            .antMatchers("/public").permitAll()
            .anyRequest().authenticated()
            .and()
            .formLogin();
    }
}
```

# Other Spring Modules
## Spring MVC (Model-View-Controller)
spring MVC is used to build web applications by separating the logic into three components - model,
view and controller.
- **Controller:** handler HTTP requests and delegates logic to services
- **Model:** contains the business logic or application's data.
- **View:** Renders the output (e.g., JSP, Thymeleaf, etc.)

## Spring Data
It simplifies database interactions. It integrates with JPA, MongoDB, Redis, and many more.
- **Spring Data JPA:** Builds on top of Hibernate and provides a simple way to interact with
relational databases.
- **Repository patterns:** spring data uses repositories for handling database operations.

## Spring Security
Spring Security is used to secure applications by handling Authentication (who you are) and
Authorization (what you are allowed to do). You can configure security via annotations or configurations
for both web and service layers. It supports integration with OAuth, LDAP, etc.

## Spring Transaction Management
Spring provides declarative transaction management via `@Transactional` annotation. It can be applied
to methods or classes and manages transaction boundaries automatically.

## Spring Cloud
It extends the Spring ecosystem to build microservices and distributed systems. It offers tools for:
- Service discovery (Eureka)
- Load balancing (Ribbon)
- Circuit breakers (Hystrix)
- Distributed tracing (Sleuth)

## Testing with Spring
Spring supports testing with `@SpringBootTest` annotation, which boots up the Spring context for
integration tests. You can also use mock objects with libraries like Mockito or MockMvc for testing
controllers.

## Further tools
- **Spring REST:** Build RESTful web services
- **Spring Reactive:** work with non-blocking, reactive programming.
- **Spring Batch:** Large-scale batch processing.
- **Spring Integration:** Message-driven architecture for enterprise applications.
