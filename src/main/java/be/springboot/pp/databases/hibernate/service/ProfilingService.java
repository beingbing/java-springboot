package be.springboot.pp.databases.hibernate.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

// Step 1: Create an interface
interface Serve {
    String performTask();
    int calculate(int x, int y);
}

// Step 2: Implement the interface
class ServiceImpl implements Serve {
    @Override
    public String performTask() {
        System.out.println("Task performed.");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return "Task completed!!";
    }

    @Override
    public int calculate(int x, int y) {
        return x + y;
    }
}

// Step 3: Create an InvocationHandler for profiling
class ProfilingHandler implements InvocationHandler {
    private final Object target;

    public ProfilingHandler(Object target) {
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        long startTime = System.nanoTime();
        // Invoke the actual method
        Object result = method.invoke(target, args);
        long endTime = System.nanoTime();

        System.out.println("Method " + method.getName() + " took " + (endTime - startTime) + " ns.");
        return result;
    }
}

class LoggingHandler implements InvocationHandler {
    private final Object target;

    public LoggingHandler(Object target) {
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("Method " + method.getName() + " called.");
        // Invoke the actual method
        Object result = method.invoke(target, args);

        System.out.println("Method " + method.getName() + " returned.");
        return result;
    }
}

// Step 4: Use Proxy to create a profiling proxy
@Slf4j
@Service
public class ProfilingService {

    @EventListener(ContextRefreshedEvent.class)
    public void doProfiling() {
        // Create the original service
        Serve service = new ServiceImpl();

        Serve logProxy = (Serve) Proxy.newProxyInstance(
                Serve.class.getClassLoader(),
                new Class[]{Serve.class},
                new LoggingHandler(service)
        );

        // Create a proxy for the service
        Serve profileProxy = (Serve) Proxy.newProxyInstance(
                Serve.class.getClassLoader(),
                new Class<?>[]{Serve.class},
                new ProfilingHandler(logProxy)
        );

        // logProxy and profileProxy is an example of proxy chaining, just like interceptor chaining.

        log.info("service: is Proxy class: {}", Proxy.isProxyClass(service.getClass()));
        log.info("logProxy: is Proxy class: {}", Proxy.isProxyClass(logProxy.getClass()));
        log.info("profileProxy: is Proxy class: {}", Proxy.isProxyClass(profileProxy.getClass()));

        // Call methods on the proxy instance
        profileProxy.performTask(); // This will be profiled
        profileProxy.calculate(5, 3); // This will also be profiled
    }
}
