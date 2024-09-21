# Spring
It is a framework for building Java Applications. It provides a wide range of tools and abstractions.
The foundation feature of the Spring framework is called Spring Core, which is responsible for
managing components, their dependencies, and how they interact. Out of the box, it provides IoC
container and DI implementation, which is responsible for managing object lifecycles and resolving
dependencies between them. It promotes loose coupling via AOP.

## Inversion of Control (IoC)
Its a design principle where the control of object creation, configuration and management is handled
by framework, rather than being managed within the application code.

## Dependency Injection
It is a design pattern that implements IoC, enabling dependency objects (services, repositories, etc.)
to be injected into a class, rather than being created within the class. Its types are
- Constructor based
- setter-based
- field-based (via reflection)

## Spring AOP (Aspect-Oriented Programming)
AOP allows handling cross-cutting concerns (like logging, security, transaction management, etc.)
separately from the main business logic. This keeps business code clean and focused. It provides -
- **Aspect:** Modularization of cross-cutting concern.
- **Join Point:** A point in the execution of the program, such as method invocation.
- **Advice:** Defines the action to take at a join point (e.g., Before/After method execution)
- **Pointcut:** Defines the execution points where aspects should be applied. A predicate that matches
join points.

## Spring Bean Lifecycle
Spring manages objects called beans. It creates them, manages their lifecycle, and destroys them.
The lifecycle is -
- **Instantiation:** Spring creates the bean.
- **Property setting:** Spring injects dependencies into the bean.
- **Initialization:** Custom initialization logic can be applied using `@PostConstruct` or by
implementing `InitializingBean`
- **Destruction:** Custom destruction logic can be added using `@PreDestroy` or by implementing
`DisposableBean`

## Key annotations in Spring
- `@Component`: indicates a class is a Spring-managed bean.
- `@Autowired`: for dependency injection into a class
- `@Configuration`: indicates that a class contains Spring bean definitions.
- `@Service`: A specialization of `@Component` for service-layer beans.
- `@Repository`: A specialization of `@Component` for data access objects (DAOs).
- `@Controller`: indicates a class as a Spring MVC controller
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
