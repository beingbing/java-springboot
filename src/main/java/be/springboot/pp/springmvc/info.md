# HTTP Basics
## Definition of HTTP
HTTP (Hypertext Transfer Protocol) is an application layer protocol used for transferring information across the web. The information to be transmitted is formatted as a structured text containing details of a destination in the form of a link/url/path. The text present has distinct components, each with a unique role.

## Stateless Nature of HTTP
HTTP is stateless, meaning each request from a client to a server is independent, and the server does not store any state or session information about the client between requests. Example, Iterator is stateful but pagination API sending page info is not.

### Benefits of Statelessness
- **Scalability:** Since the server doesn’t need to maintain client context, any server can handle a request, making load balancing easier.
- **Interoperability:** Stateless architecture makes it easier for different systems to interact seamlessly.

### How Do Recommendation Systems Work if HTTP is Stateless?
Because HTTP is stateless, technologies like cookies and tokens are used to maintain session information between the client and server. These are passed as part of the HTTP request, enabling the server to recognize the client and provide personalized content. Here's a brief overview:
- **Cookies:** Stored on the client side, used to remember user sessions, preferences, etc.
- **Tokens (JWT - JSON Web Tokens):** Tokens can be sent with each request to authenticate users and maintain their session.
```java
// This example demonstrates how to create, set, and read cookies within a Java servlet or Spring controller.
// Example of setting a cookie in an HTTP response
Cookie userSession = new Cookie("user-session", "session-id-123");
userSession.setMaxAge(60 * 60); // 1 hour validity
response.addCookie(userSession);

// Reading a cookie from the request
Cookie[] cookies = request.getCookies();
if (cookies != null) {
    for (Cookie cookie : cookies) {
        if ("user-session".equals(cookie.getName())) {
            String sessionId = cookie.getValue();
            // Process the session ID...
        }
    }
}
```

## Components of an HTTP Request
An HTTP request consists of several components, each serving a unique role in client-server communication:
- **Method:** Defines the action to be performed (e.g., GET, POST, PATCH, PUT, DELETE).
- **URL:** The resource's location. Should be highly readable. (e.g., /book/123).
- **Version:** HTTP versions, with newer versions supporting optimizations like persistent/keep-alive connections.
- **Headers:** Meta-information (e.g., Content-Type: application/json, Authorization, etc.) for content negotiation and request handling.
- **Body:** Optional, if added contains the request data, typically in POST and PUT requests.
```shell
GET /books/search?title=java HTTP/1.1
Host: www.bookstore.com
Authorization: Bearer token-abc123

# In this request, we are making a GET call to search for books with the title "java" on a bookstore website. The Authorization header contains a token to authenticate the request.
```
- **path-variable:** Optional to add, but strictly compulsory if declared in an URL. Part of the URL, used to uniquely define resource and at the same time get more details on what resource is requested. Usually composed of values which are mandatory, unique, small and do not compromise with readability, e.g. - ids in URL. Also known as **request-path**.
- **request-params:** Optional to add, but can be made optional even after declaring in a request. Used as a key-value pair appended to URL after `?`. Usually added for optional fields or when values of keys are large, e.g.—for adding filters, searches, etc.
```java
// Example: Using path variables and request parameters
@GetMapping("/books/{id}")
public Book getBookById(@PathVariable("id") Long bookId) {
    // Retrieve the book by ID
    return bookService.findBookById(bookId);
}

@GetMapping("/books/search")
public List<Book> searchBooks(@RequestParam("title") String title) {
    // Search for books by title
    return bookService.searchBooksByTitle(title);
}
```

## Components of an HTTP Response
- **Status Code:** Numerical code representing the result of the request (e.g., 200 OK, 404 Not Found).
- **Status Message:** Accompanies the status code to provide additional information.
- **Headers:** Meta-information about the response, like `Content-Type` or `Set-Cookie`.
- **Body:** The actual content of the response can be in HTML, JSON, or XML format.
```shell
HTTP/1.1 200 OK
Content-Type: application/json
{
  "id": 123,
  "title": "Effective Java",
  "author": "Joshua Bloch"
}
```
```java
@GetMapping("/books/{id}")
public ResponseEntity<Book> getBookById(@PathVariable("id") Long bookId) {
    Book book = bookService.findBookById(bookId);
    if (book != null) {
        return ResponseEntity.ok(book); // HTTP 200 OK
    } else {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null); // HTTP 404 Not Found
    }
}
```

# Servlet
An HTTP server acts as an intermediary between OS and Spring MVC dispatcher-servlet, listens to a port, accepts incoming HTTP requests from kernel (OS) and forward them to forward-controller. In Java, it is called as servlet, which can be understood as 'let us serve'. Servlets operate within an environment called a Servlet Container. Examples of servlet containers include Tomcat and Jetty.

## Servlet Lifecycle:
A Servlet is defined by implementing the `javax.servlet.Servlet` interface, which includes three key methods:
- `init()`: Initializes the servlet with necessary resources and dependencies.
- `service()`: This method handles incoming requests and sends appropriate responses. It delegates the request to Spring MVC.
- `destroy()`: Cleans up resources before the servlet is shut down.

## Traditional Deployment and its Challenges
In pre-Spring framework days, developers would often generate a JAR file using Maven (`mvn install`) in `/target` folder and deploy it manually on a standalone server. This was common in pre-containerized environments, as containerization ensured that application and server share the same environment. However, this approach had several drawbacks:
### Compatibility issues:
- The server's Java version might differ from the application's Java version, leading to runtime problems.
- **Single Point of Failure (SPOF):** If the server failed, all applications running on it would be affected.
- **Difficulties in managing multiple applications:** Each application might require a different server configuration, making it hard to manage on a single server instance.

Despite these drawbacks, standalone servers had the benefit of running multiple applications at once.

# Spring MVC
Spring MVC enables web application development. It is built on top of the Servlet API. It follows the Model-View-Controller design pattern, which helps in separating concerns:
- **Model:** Represents the application data and business logic.
- **View:** Responsible for rendering the user interface.
- **Controller:** Handles user input and interacts with the Model to update the view.

## Controllers
In Spring MVC, controllers are the components that consume HTTP requests and generate responses.

### @Controller
This is a Spring MVC component that marks a class as a web controller. By default, methods in a @Controller return views (HTML templates). To send raw responses (such as JSON), you need to use `@ResponseBody`.
```java
@Controller
@RequestMapping("/simple")
public class SimpleController {

  @GetMapping
//  @ResponseBody // to send raw response with @Controller
  public String hello() {
    return "hello"; // If a return type is a String, then responds with template having the name "hello"
  }
}
```

### @RestController
Spring MVC supports building RESTful web services using `@RestController`. It combines `@Controller` and `@ResponseBody` by automatically converting Java objects into JSON (or XML) using Jackson (or another library) via custom response class getters/setters (@Getter/@Setter can also be used) and writing it directly to the HTTP response body. The conversion ability of jackson is abstracted in `HTTPMessageConverter` interface. Spring MVC comes with only Java objects to JSON serialization/deserialization implementation of `HTTPMessageConverter` interface out of the box. If serialization/deserialization of any other format (like xml) is required, then its implementation needs to be imported as a dependency (`jackson-dataformat-xml`).
```java
// produces: response produced is serialized into an XML file
@RequestMapping(method = RequestMethod.GET, value = "result", produces = "application/xml")
public ExamResult getExamResult() {
    System.out.println("Received result request");
    return new ExamResult(70, 80, 90);
}
```

#### Note:
Usually `GET` request do not contain a body, as we do not prefer too much data via `GET method. So, Spring Jackson has capability to bind HTTP request-params into a request body object by using its constructor/setter (if it can be translated).
```java
// Do not use it extensively, as it is not reliable and not a good practice as well.
// Curl --location 'http://localhost:8080/simple/result/examine?physics=70&chemistry=65&maths=80'
@RequestMapping(method = RequestMethod.GET, value = "result/examine")
public String examineResult(ExamResult examResult) {
    System.out.println("Received examine result request with " + examResult);
    if ((double)examResult.getTotal() /examResult.getMaxScore() >= 0.7) {
        return "passed";
    }
    return "failed";
}
```

## @RequestMapping
It is a functional interface and has features like -
- `path`/`value`: It maps HTTP request path to handler methods written in MVC controllers.
- `method`: type of HTTP request method
- `produces`: response type produced while serialization
- `consumes`: request body type that is consumed for deserialization
- `headers`
- `params`
```java
@Controller
@RequestMapping("/api")
public class ApiController {

    @RequestMapping("/data")
    public String getData(Model model) {
        model.addAttribute("data", "Some API data");
        return "dataView";
    }
}
```

## @RequestParam
example on how to use it -
```java
// http://localhost:8080/simple/greet?name=samar&say=goodbye
@RequestMapping(method = RequestMethod.GET, value = "/greet")
public String greetWithParam(
        @RequestParam(value = "name", defaultValue = "World") String name) {
    System.out.println("Received request with param 'name' " + name);
    return String.format("Hello %s!", name);
}
```

## @PathVariable
example on how to use it -
```java
// http://localhost:8080/simple/greet/samar
@RequestMapping(method = RequestMethod.GET, value = "/greet/{name}")
public String greetWithPath(
        @PathVariable("name") String name) {
    System.out.println("Received request with path variable 'name' " + name);
    return String.format("Good Bye %s!", name);
}
```

## DispatcherServlet
Servlet exists in TomCat, and it hands over received request to the DispatcherServlet. It is the Front Controller and starting point of a Spring MVC framework. After receiving request, DispatcherServlet routes it to the appropriate controller based on the `HandlerMapping` (controller methods mapped to a route). Inside DispatcherServlet, The request goes through chain of interceptors (in order of declaration) before reaching the controller and the response generated also travels back through all the interceptors (in reverse order of declaration) before being handed over to the servlet.

## Design Pattern: Front Controller
The Front Controller pattern is used in Spring MVC to centralize request handling. It is composed of -
- `HandlerExecutionChain`
- `HandlerInterceptor`
- `HandlerMethod`
- `HandlerAdapter`
```java
public class DispatcherServlet extends FrameworkServlet {
    @Override
    protected void doDispatch(HttpServletRequest request, HttpServletResponse response) throws Exception {
        // Finds the handler (controller method) based on URL
        HandlerExecutionChain handler = getHandler(request);
        
        // Executes preHandle() methods of interceptors
        preHandle(request, response, handler);
        
        // Executes the handler (controller)
        HandlerAdapter ha = getHandlerAdapter(handler.getHandler());
        ha.handle(request, response, handler.getHandler());

        // Executes postHandle() methods of interceptors
        postHandle(request, response, handler);
    }
}

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Bean
    public DispatcherServlet dispatcherServlet() {
        return new DispatcherServlet();
    }

    @Bean
    public ServletRegistrationBean<DispatcherServlet> dispatcherServletRegistration() {
        ServletRegistrationBean<DispatcherServlet> registration = new ServletRegistrationBean<>(dispatcherServlet(), "/");
        registration.setName("dispatcherServlet");
        return registration;
    }
}
```

### HandlerExecutionChain
During creation of `ApplicationContext`, a list of `HandlerMapping` is built, which is a mapping of route against handler. Once the route of request is resolved, `handler` associated with it is fetch, from which complete `HandlerExecutionChain` is extracted. It is composed of -
- The list of eligible interceptors (in order of declaration) that will be executed before (`preHandle()`) and after (`postHandle()`) the handler.
- The `handler` (controller method) that will process the request.

### HandlerMethod
In Java, when a method is loaded into memory, it's converted into an object of the `Method` class. In a Spring MVC controller, methods mapped with the `@RequestMapping` annotation are first converted into `Method` instances, then upcasted to be an instance of class `Object` and last, wrapped inside an instance of`HandlerMethod` class. This upcasted `Object` instance inside  `HandlerMethod` object is designated as the `handler` by the DispatcherServlet.

### HandlerAdapter
Since the `handler` is cast to an `Object`, it lacks any specific functionality. To manage this, the `DispatcherServlet` maintains a list of `HandlerAdapter` instances, each specialized in handling a particular type of `handler`. The `DispatcherServlet` checks each `HandlerAdapter` by calling its `supports()` method to see if it can handle the given `handler` after downcasting. Once a suitable `HandlerAdapter` is found, it is kept ready. After successfully executing all `preHandle()` methods of the `HandlerInterceptor` instances in the `HandlerExecutionChain` for that `handler`, the `DispatcherServlet` calls the `handle()` method of the `HandlerAdapter`, which adapts the `handler` and triggers the appropriate logic to generate a response.

### HandlerInterceptor
Interceptors in Spring MVC allow cross-cutting concerns to be addressed. Concerns which are not a part of business logic, like logging the request, measuring API latency, adding missing header or processing headers. They allow you to intercept and process requests before they reach the controller or after the response is generated. They are created by implementing `HandlerInterceptor` interface, and registered using `WebMvcConfigurer`. It has three method declarations -
- `preHandle()`: invoked before request is handed over to controller.
- `postHandle()`: invoked when controller has generated a response but before handing over the response to server.
- `afterCompletion()`: executed after the complete request has been processed and/or the view has been rendered. It’s ideal for resource cleanup tasks or logging.
```
Client Request -> Pre-Handle -> Controller -> Post-Handle -> View Resolver -> View Rendered -> After Completion -> Client Response
```
```java
@Component
public class SimpleInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("SimpleInterceptor: preHandle " + request);
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable ModelAndView modelAndView) throws Exception {
        System.out.println("SimpleInterceptor: postHandle " + request);
    }

}

@Component
public class SecondInterceptor implements HandlerInterceptor {

  @Override
  public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
    System.out.println("SecondInterceptor: preHandle " + request);
    return true;
  }

  @Override
  public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable ModelAndView modelAndView) throws Exception {
    System.out.println("SecondInterceptor: postHandle " + request);
  }

}

@Component
public class MvcConfig implements WebMvcConfigurer {

    @Autowired
    private SimpleInterceptor simpleInterceptor;

    @Autowired
    private SecondInterceptor secondInterceptor;

    /*
     * InterceptorRegistry is a bean managed by Spring. At the time of its creation,
     * WebMvcConfigurer.addInterceptors() is called and custom interceptors are
     * added. Then Spring-MVC will use them as middleware.
     * */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {

        registry.addInterceptor(simpleInterceptor);

        registry.addInterceptor(secondInterceptor).excludePathPatterns("/simple/result");

        registry.addInterceptor(authInterceptor).addPathPatterns("/auth/**");
    }
}
```
- When `http://localhost:8080/simple/greet/samar` request came, order was -
  - `SimpleInterceptor: preHandle` org.apache.catalina.connector.RequestFacade@510cb46d
  - `SecondInterceptor: preHandle` org.apache.catalina.connector.RequestFacade@510cb46d
  - `Received request with path variable 'name' samar`
  - `SecondInterceptor: postHandle` org.apache.catalina.connector.RequestFacade@510cb46d
  - `SimpleInterceptor: postHandle` org.apache.catalina.connector.RequestFacade@510cb46d
- After adding `/result` path in `.excludePathPatterns()` for `SecondInterceptor` then when `http://localhost:8080/simple/result` request came, order was -
  - `SimpleInterceptor: preHandle` org.apache.catalina.connector.RequestFacade@6cba67d9
  - `Received result request`
  - `SimpleInterceptor: postHandle` org.apache.catalina.connector.RequestFacade@6cba67d9

## Views

### View Rendering in Spring
When a controller returns a view name (typically as a String), it's the job of the `ViewResolver` to resolve this logical view name into an actual view implementation, such as an HTML page, JSP, Thymeleaf template, or even a JSON/XML output. `HandlerMethodReturnValueHandler` is the interface responsible for handling return types of controller methods. One of its implementation `ViewNameMethodReturnValueHandler` is responsible for looking out for a view file if a controller returned type is a String. But if return type is not a `String` but a custom object then we need to define how to handle it by defining our own Custom Handler.

### Usage of HandlerMethodReturnValueHandler
`HandlerMethodReturnValueHandler` interface has two method declarations -
- `boolean supportsReturnType(MethodParameter returnType)` is used to check if a return type is supported by an implementation. `MethodParameter` is used to get the return type of the `HandlerMethod`. It might be `String.class`, `Double.class`, etc.
- if the above method returns `true`, then we have `handleReturnValue()` which is used to then handle the supported return type.

### Custom Return Type Handlers

#### Thymeleaf Configuration
- add below lines as environment variables for `ViewResolver`
```properties
# Thymeleaf Configuration
spring.thymeleaf.prefix=classpath:/templates/
spring.thymeleaf.suffix=.html
```
- and add below package
```xml
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-thymeleaf</artifactId>
		</dependency>
```
- create a folder in `/src/main/resources` directory and name it `templates`
```html
<!-- hello.html file content with path: /src/main/resources/templates/hello.html -->
<!doctype html>
<html lang="en">
<head>
  <title>Our Funky HTML Page</title>
  <meta name="description" content="Our first page">
  <meta name="keywords" content="html tutorial template">
</head>
<body>
<h1>Content goes here.</h1>
</body>
</html>
```
- put `.html` view files in it and `ViewNameMethodReturnValueHandler` will locate it.
- now compile and start the project.

### How Spring processes return types like ResponseEntity.

### Note:
- when exception is thrown we need to translate it to an error code and return suitable response, `DispatcherServlet` has `HandlerExceptionResolver` for this purpose.

## Filter
- very similar to spring-mvc `Interceptors` there is another component by the name `Filter` which is a part of TomCat.
- conceptually `Interceptors` and `Filters` are same, although designed differently.
- proxy checks, caching, basic authentications all are applied on `Filters` extensively.
- `Filter` is provided by TomCat as an interface.
- every `Filter` once completes execution, invokes next `Filter`
- once custom `Filter` are created they get added to filter chain automatically

## Summary: Flow of Spring MVC
- Servlet-container (Tomcat) listens to a webSocket and receives a request from kernel (OS)
- request passes through `Filters` in TomCat and reaches servlet
- TomCat servlet then forwards request to spring-mvc
- spring-mvc class which accepts the request from servlet-container is `DispatcherServlet`
- `DispatcherServlet` is responsible for dispatching request to the correct controller via request-mapping routes, but during that, request before reaching to a controller passes through `Interceptors`.
- when controller generates response it passes again through interceptors in reverse order.
- when response reaches `DispatcherServlet`, response is analyzed for any thrown exception to resolve it, or if it's a view then do suitable action on it.
- once `DispatcherServlet` hands over response back to TomCat servlet which then is made to pass `Filters` again in reverse order back on its way to get handed over to OS.
