package com.aop.demo.config;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
import javax.sql.DataSource;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PrimaryDruidConfiguration extends BaseDruidConfig {


  @Bean(name = "primaryDataSource")
  @ConfigurationProperties("spring.datasource.druid.one")
  public DataSource dataSourceOne() {
    return DruidDataSourceBuilder.create().build();
  }
}
