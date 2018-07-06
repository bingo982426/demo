package com.aop.demo.config;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
import javax.sql.DataSource;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SecondDruidConfiguration extends BaseDruidConfig {


  @Bean(name = "secondDataSource")
  @ConfigurationProperties("spring.datasource.druid.two")
  public DataSource dataSourceTwo() {
    return DruidDataSourceBuilder.create().build();
  }
}
