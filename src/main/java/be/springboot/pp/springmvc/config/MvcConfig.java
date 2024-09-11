package be.springboot.pp.springmvc.config;

import be.springboot.pp.authentication.interceptors.AuthInterceptor;
import be.springboot.pp.springmvc.exceptions.CustomExceptionHandler;
import be.springboot.pp.springmvc.interceptor.SecondInterceptor;
import be.springboot.pp.springmvc.interceptor.SimpleInterceptor;
import be.springboot.pp.springmvc.returnvaluehandler.SampleReturnTypeHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.method.support.HandlerMethodReturnValueHandler;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

/*
* When Spring will create Bean, `addInterceptors()` will be called and
* custom interceptors will be added in the registry.
*
* Interceptors are executed in the order they are added.
* */
@Component
public class MvcConfig implements WebMvcConfigurer {

    @Autowired
    private SimpleInterceptor simpleInterceptor;

    @Autowired
    private SecondInterceptor secondInterceptor;

    @Autowired
    private SampleReturnTypeHandler sampleReturnTypeHandler;

    @Autowired
    private AuthInterceptor authInterceptor;

    /*
    * InterceptorRegistry is a bean already created by Spring, we can add our custom
    * interceptors in it so that Spring-MVC can use them as middleware as well.
    * */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {

        registry.addInterceptor(simpleInterceptor);

        registry.addInterceptor(secondInterceptor).excludePathPatterns("/simple/result");

        registry.addInterceptor(authInterceptor).addPathPatterns("/auth/**");
    }

    @Override
    public void addReturnValueHandlers(List<HandlerMethodReturnValueHandler> handlers) {
        handlers.add(sampleReturnTypeHandler);
    }

    /*
    * In previous example, we created a Bean of our exception-handler and have used `HandlerExceptionResolverComposite` which
    * is a Bean. Spring will initialize our exception-handler as a Bean, and during runtime exception-resolvers list is populated.
    *
    * But here, we are creating our own exception-handler bean and adding it in the list of exception-resolvers.
    * */
    @Override
    public void configureHandlerExceptionResolvers(List<HandlerExceptionResolver> resolvers) {
        resolvers.add(new CustomExceptionHandler());
        for (HandlerExceptionResolver her : resolvers)
            System.out.println("Exception resolvers: " + her.getClass().getName());
    }

}
