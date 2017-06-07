package ddd.demo.infrastructure.repository;

import ddd.demo.domain.order.repository.IOrderRepository;
import ddd.demo.infrastructure.repository.order.OrderRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Import;

@Configuration
@EnableAspectJAutoProxy
@Import({RepositoryInterceptor.class, DataSourceConfig.class})
public class RepositoryConfig {
    @Bean
    public IOrderRepository orderRepository(){
        return new OrderRepository();
    }
}