# Events
Events in Spring Boot provide a way for different components within an application to communicate asynchronously without tight coupling. Events are particularly useful when you want to decouple components and notify one part of the system about certain changes or actions that occur elsewhere. In Spring Boot, events follow the publish-subscribe pattern, events are handled using the `ApplicationEventPublisher` and `@EventListener` annotations.

### Note:
`ApplicationContext` extends `ApplicationEventPublisher` hence, whenever SpringBoot emits an event associated with lifecycle, the source is `ApplicationContext`.
So, for `private ApplicationEventPublisher applicationEventPublisher`, the object injected in `applicationEventPublisher` by SpringBoot in case of SpringBoot emitted events will be the object of `ApplicationContext` created during initialization of application.

## Built-in Spring Boot Events
Spring Boot fires some events automatically at key stages of the application lifecycle:
- `ApplicationStartingEvent`: Triggered when the application is starting, but before the ApplicationContext is created.
- `ApplicationEnvironmentPreparedEvent`: Fired when the Environment is prepared, but before the ApplicationContext is created.
- `ApplicationPreparedEvent`: Occurs when the ApplicationContext is prepared, but before any beans are loaded.
- `ApplicationStartedEvent`: Fired when the application context is completely created and the application has started.
- `ApplicationReadyEvent`: Triggered when the application is ready to service requests.
- `ApplicationFailedEvent`: Fired when the application fails to start.
- `ContextClosedEvent`: Fired when application context is forcefully closed.

## Transactional Events
Spring provides support for handling events within the context of a transaction. You can use the `@TransactionalEventListener` annotation to listen for events only if the current transaction successfully commits.
```java
import org.springframework.transaction.event.TransactionalEventListener;
import org.springframework.stereotype.Component;

@Component
public class UserTransactionalListener {

    @TransactionalEventListener
    public void handleUserRegisteredEvent(UserRegisteredEvent event) {
        System.out.println("Transactional listener for user: " + event.getUsername());
        // Perform transactional work, e.g., logging to the database
    }
}
```
In this case, the event listener will only be invoked after a transaction is successfully committed, ensuring consistency between the event and any associated database operations.

## Asynchronous Event Listeners
Spring allows you to handle events asynchronously, meaning the listener will process the event in a separate thread. To enable this, you can annotate the listener method with `@Async` in combination with `@EventListener`. You also need to enable asynchronous processing in your Spring Boot application by adding `@EnableAsync` in your configuration.
```java
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
public class AsyncUserEventListener {

    @Async
    @EventListener
    public void handleUserRegisteredEvent(UserRegisteredEvent event) {
        System.out.println("Asynchronously handling event for user: " + event.getUsername());
        // Perform async operations, like sending an email
    }
}
```
Configuration to Enable Asynchronous Processing:
```java
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;

@Configuration
@EnableAsync
public class AsyncConfig {
    // Any additional async configuration goes here
}
```

## Conditional Event Listeners
You can conditionally listen for events based on certain criteria using the condition attribute of `@EventListener`. This allows you to listen for events only when a specific condition is true.
```java
@Component
public class ConditionalEventListener {

    @EventListener(condition = "#event.username == 'admin'")
    public void handleAdminUserEvent(UserRegisteredEvent event) {
        System.out.println("Handling event for admin user: " + event.getUsername());
    }
}
```
In this example, the event listener will only be invoked if the username in the event is equal to 'admin'.

## Hierarchical Event Publishing
Spring's event system also supports publishing events across a hierarchy of ApplicationContexts. Child contexts can publish events that are received by listeners in parent contexts, but not the other way around.

## Miscellaneous
- what is Observation/Observer Pattern ?
- event-driven/event-based system ?
- What events SpringBoot application emits ?
- Are all events application-wide or can we make them package specific ?