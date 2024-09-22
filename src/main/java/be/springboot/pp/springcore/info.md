# Spring
It is a framework for building Java Applications, responsible for managing components, their dependencies,
and how they interact. It provides a wide range of tools and abstractions. The foundation feature
of the Spring framework is called Spring Core. Out of the box, it provides IoC container and DI
implementation, which is responsible for managing object lifecycles and resolving dependencies
between them.

Spring Core abstracts the creation and management of objects (beans), providing mechanisms for
configuration and wiring components together. It enables developers to focus on business logic rather
than on boilerplate code for object management.

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
into it by the framework (the IoC container), rather than being created within the class. Its types are -
- Constructor based: Dependencies are provided through the constructor. **Most recommended because
it makes the object immutable and enforces dependency injection at the time of object creation.**
```java
public class MyService {
    private final MyRepository repository;

    @Autowired
    public MyService(MyRepository repository) {
        this.repository = repository;
    }
}
```
- setter-based: Dependencies are provided through setter methods.
```java
public class MyService {
    private MyRepository repository;

    @Autowired
    public void setRepository(MyRepository repository) {
        this.repository = repository;
    }
}
```
- field-based (via reflection) (not recommended due to reduced testability): Dependencies are directly
injected into fields using @Autowired
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
- Java-based configuration
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
Spring can automatically resolve and inject beans by their type using autowiring. It offers several
modes of autowiring:
- **No:** Default mode where no autowiring is performed.
- **ByType:** Matches by the type of bean.
- **ByName:** Matches by the bean's name.
- **Constructor:** Uses constructor injection.

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
```java
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
- `@Component`: indicates a class is a Spring-managed bean.
- `@Autowired`: for dependency injection into a class
- `@Bean`: used to define a bean in Java-based configuration.
- `@Configuration`: indicates that a class contains Spring bean definitions.
- `@Service`: A specialization of `@Component` for service-layer beans.
- `@Repository`: A specialization of `@Component` for data access objects (DAOs).
- `@Controller`: A specialization of `@Component` indicating a class as a Spring MVC controller
- `@RestController`: a combination of `@Controller` and `@ResponseBody` for REST APIs
- `@EnableAutoConfiguration`: Automatically configures Spring Boot applications based on the classpath.

## Spring Boot
It is a module of Spring that allows you to create stand-alone applications with embedded servers.
It removes boilerplate configurations and uses `convention over configuration`.
It simplifies Spring development by providing an opinionated setup, where you don't need to define everything from scratch. It enables -
- **Auto-configuration:** Spring Boot automatically configures the application beans based on the dependencies on the classpath and other conditions
- **Embedded Servers:** You can run your application with an embedded server (e.g., Tomcat, Jetty), eliminating the need for external deployment.
- **Opinionated Defaults:** It provides sensible defaults for configurations, which developers can override when necessary.
- **Starter Dependencies:** Instead of adding individual dependencies, Spring Boot provides "starter"
dependencies (like, `spring-boot-starter-web`) that includes everything you need for a particular use
case.

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
