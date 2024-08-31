package be.springboot.pp.springmvc.interceptor;

import be.springboot.pp.springmvc.returnvaluehandler.SampleReturnTypeHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.method.support.HandlerMethodReturnValueHandler;
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
public class InterceptorConfig implements WebMvcConfigurer {

    @Autowired
    private SimpleInterceptor simpleInterceptor;

    @Autowired
    private SecondInterceptor secondInterceptor;

    @Autowired
    private SampleReturnTypeHandler sampleReturnTypeHandler;

    /*
    * InterceptorRegistry is a bean already created by Spring, we can add our custom
    * interceptors in it so that Spring-MVC can use them as middleware as well.
    * */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {

        registry.addInterceptor(simpleInterceptor);

        registry.addInterceptor(secondInterceptor).excludePathPatterns("/simple/result");
    }

    @Override
    public void addReturnValueHandlers(List<HandlerMethodReturnValueHandler> handlers) {
        handlers.add(sampleReturnTypeHandler);
    }

}
