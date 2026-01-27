package com.tajwebapp.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Component;

@Component
@Aspect
@EnableAspectJAutoProxy
public class DeprtmentServiceLoggingAspect {
    public static final Logger LOGGER = LoggerFactory.getLogger(DeprtmentServiceLoggingAspect.class);

    @Pointcut("execution(* com.tajwebapp.service.DepartmentService.*(..))")
    public void departmentServiceMethods(){}

    @Before("departmentServiceMethods()")
    public void logBefore(JoinPoint jp){
        LOGGER.info("Method Called: " + jp.getSignature().getName());
    }

    @After("deparmentServiceMethods()")
    public void logAfter(JoinPoint jp){
        LOGGER.info("Method executed: " + jp.getSignature().getName());
    }

    @AfterThrowing("departmentServiceMethods()")
    public void logAfterThrowing(JoinPoint jp){
        LOGGER.info("Method has some issue: " + jp.getSignature().getName());
    }

    @AfterReturning("departmentServiceMethods()")
    public void logAfterReturning(JoinPoint jp){
        LOGGER.info("Method executed successfully: " + jp.getSignature().getName());
    }
}
