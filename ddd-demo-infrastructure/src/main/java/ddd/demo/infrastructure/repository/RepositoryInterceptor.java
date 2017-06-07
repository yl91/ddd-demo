package ddd.demo.infrastructure.repository;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class RepositoryInterceptor {
    @Order(1)
    @Before(value = "execution(public * ddd.demo.infrastructure.repository..*.*Repository.*(..))")
    public void before1(JoinPoint joinPoint) {
        System.out.print("before1= ");
        System.out.println(joinPoint.getSignature().getName());
    }
    @Order(2)
    @Before(value = "execution(public * ddd.demo.infrastructure.repository..*.*Repository.*(..))")
    public void before2(JoinPoint joinPoint) {
        System.out.print("before2= ");
        System.out.println(joinPoint.getSignature().getName());
    }

    @AfterReturning(value = "execution(public * ddd.demo.infrastructure.repository..*.*Repository.*(..))")
    public void afterReturing(JoinPoint joinPoint) {
        System.out.print("AfterReturning= ");
        System.out.println(joinPoint.getSignature().getName());
    }

    @AfterThrowing(value = "execution(public * ddd.demo.infrastructure.repository..*.*Repository.*(..))",throwing = "e")
    public void exception(JoinPoint joinPoint,Exception e) {
        System.out.print("afterthrowing= ");
        System.out.println(joinPoint.getSignature().getName());
        System.out.println(e.getClass().getName());
    }

    @After(value = "execution(public * ddd.demo.infrastructure.repository..*.*Repository.*(..))")
    public void after(JoinPoint joinPoint) {
        System.out.print("after= ");
        System.out.println(joinPoint.getSignature().getName());
    }
}