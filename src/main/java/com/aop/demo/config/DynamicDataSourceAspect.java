package com.aop.demo.config;

import java.lang.reflect.Method;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Aspect
@Order(-1)
@Component
public class DynamicDataSourceAspect {

  private static final String GET_METHOD = "get";

  @Before("execution(* com.aop.demo.service.impl..*(..))")
  public void switchDS(JoinPoint joinPoint) {
    Signature className = joinPoint.getSignature();
    MethodSignature ms = (MethodSignature) className;
    Method m = ms.getMethod();

    if (m.getName().startsWith(GET_METHOD)) {
      DataSourceContextHolder.setDB("secondDataSource");
    } else {
      DataSourceContextHolder.setDB("primaryDataSource");
    }
  }

  @After("execution(* com.aop.demo.service.impl..*(..))")
  public void after(JoinPoint joinPoint) {
    DataSourceContextHolder.clearDB();
  }
}