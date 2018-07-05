package com.aop.demo.config;

import java.lang.reflect.Method;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Aspect
@Order(1)
@Component
public class DynamicDataSourceAspect {

  @Around("execution(* com.aop.demo.service.impl..*(..))")
  public Object switchDS(ProceedingJoinPoint point) throws Throwable {
    Signature className = point.getSignature();
    MethodSignature ms = (MethodSignature) className;
    Method m = ms.getMethod();

    if (m.getName().startsWith("get")) {
      DataSourceContextHolder.setDB("datasource2");
    } else {
      DataSourceContextHolder.setDB("datasource1");
    }

    // 切换数据源

    try {
      return point.proceed();
    } finally {
      DataSourceContextHolder.clearDB();
    }
  }

}