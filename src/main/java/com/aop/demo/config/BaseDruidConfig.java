package com.aop.demo.config;

import com.alibaba.druid.filter.logging.Slf4jLogFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BaseDruidConfig {

  @Bean
  public Slf4jLogFilter logFilter() {
    Slf4jLogFilter filter = new Slf4jLogFilter();
    filter.setResultSetLogEnabled(true);
    filter.setStatementExecutableSqlLogEnable(true);
    return filter;
  }
}
