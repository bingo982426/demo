package com.aop.demo.config;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Aspect
@Order(-1)
@Component
public class DynamicDataSourceAspect {


  @Around("execution(* com.aop.demo.game.service.impl..*(..))")
  public Object primaryDataSource(ProceedingJoinPoint joinPoint) throws Throwable {
    DataSourceContextHolder.setDB("primaryDataSource");
    Object o = joinPoint.proceed();
    DataSourceContextHolder.clearDB();
    return o;
  }

  @Around("execution(* com.aop.demo.competition.service.impl..*(..))")
  public Object secondDataSource(ProceedingJoinPoint joinPoint) throws Throwable {
    DataSourceContextHolder.setDB("secondDataSource");
    Object o = joinPoint.proceed();
    DataSourceContextHolder.clearDB();
    return o;
  }
}