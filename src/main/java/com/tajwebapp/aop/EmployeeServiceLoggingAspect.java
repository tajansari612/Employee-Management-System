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
public class EmployeeServiceLoggingAspect {
    private static final Logger LOGGER = LoggerFactory.getLogger(EmployeeServiceLoggingAspect.class);

    @Pointcut("execution(public * com.tajwebapp.service.EmployeeService.*(..))")
    public void employeeServiceMethods(){}

    @Before("employeeServiceMethods()")
    public void logBefore(JoinPoint jp){
        LOGGER.info("Mehtod Called "+jp.getSignature().getName());
    }

    @After("employeeServiceMethods()")
    public void logAfter(JoinPoint jp){
        LOGGER.info("Mehtod Executed "+jp.getSignature().getName());
    }

    @AfterThrowing("employeeServiceMethods()")
    public void logMethodCrash(JoinPoint jp){
        LOGGER.info("Mehtod has some issue "+jp.getSignature().getName());
    }

    @AfterReturning("employeeServiceMethods()")
    public void logMethodExecutedSuccessfully(JoinPoint jp){
        LOGGER.info("Mehtod executed successfully "+jp.getSignature().getName());
    }
}
