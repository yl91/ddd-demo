package ddd.demo.application.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

/**
 * Created by lil.yang on 2017/6/4.
 */
@Aspect
@Component
public class ApplicationInterceptor {

    @Before(value = "within(ddd.demo.application..*) && target(easy.domain.application.BaseApplication)")
    public void beforeCall(JoinPoint joinPoint) {
        System.out.println(joinPoint.getSignature().getName());
    }
}
