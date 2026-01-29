package com.tajwebapp.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Component;

@Component
@Aspect
@EnableAspectJAutoProxy
public class EmployeeServiceLoggingAspect {
    private static final Logger LOGGER = LoggerFactory.getLogger(EmployeeServiceLoggingAspect.class);

    @Pointcut("execution(public * com.tajwebapp.service.EmployeeService.*(..))")
    public void employeeServiceMethods(){}

    @Around("employeeServiceMethods()")
    public Object logAround(ProceedingJoinPoint pjp) throws Throwable {
        long start = System.currentTimeMillis();
        String signature = pjp.getSignature().toShortString();
        Object[] args = pjp.getArgs();

        LOGGER.info("Entering {} with args={}", signature, args);
        try {
            Object result = pjp.proceed();
            long time = System.currentTimeMillis() - start;
            LOGGER.info("Exiting {} returned={} in {} ms", signature, result, time);
            return result;
        } catch (Throwable ex) {
            long time = System.currentTimeMillis() - start;
            LOGGER.error("Exception in {} after {} ms: {}", signature, time, ex.toString(), ex);
            throw ex;
        }
    }
}
