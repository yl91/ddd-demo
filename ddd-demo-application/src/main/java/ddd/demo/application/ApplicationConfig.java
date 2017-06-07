package ddd.demo.application;

import ddd.demo.application.aspect.ApplicationInterceptor;
import ddd.demo.application.order.OrderApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * Created by lil.yang on 2017/6/4.
 */
@Configuration
@ComponentScan(basePackageClasses = ApplicationInterceptor.class)
@EnableAspectJAutoProxy
public class ApplicationConfig  {
    @Bean
    public OrderApplication orderRepository(){
        return new OrderApplication();
    }
}
