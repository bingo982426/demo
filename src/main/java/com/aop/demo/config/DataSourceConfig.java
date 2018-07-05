package com.aop.demo.config;

import java.util.HashMap;
import java.util.Map;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
public class DataSourceConfig extends BaseDruidConfig {

  @Bean(name = "onep")
  @Qualifier("onep")
  @ConfigurationProperties(prefix = "spring.datasource.druid.one")
  public DataSourceProperties one() {
    return new DataSourceProperties();
  }

  @Bean(name = "twop")
  @Qualifier("twop")
  @ConfigurationProperties(prefix = "spring.datasource.druid.two")
  public DataSourceProperties two() {
    return new DataSourceProperties();
  }

  //数据源1
  @Bean(name = "datasource1")
  @ConfigurationProperties(prefix = "spring.datasource.druid.one")
  public DataSource dataSource1() {
    return one().initializeDataSourceBuilder().build();
  }

  //数据源2
  @Bean(name = "datasource2")
  @ConfigurationProperties(prefix = "spring.datasource.druid.two")
  public DataSource dataSource2() {
    return two().initializeDataSourceBuilder().build();
  }

  @Primary
  @Bean(name = "dynamicDataSource")
  public DataSource dynamicDataSource() {
    DynamicDataSource dynamicDataSource = new DynamicDataSource();
    dynamicDataSource.setDefaultTargetDataSource(dataSource1());
    Map<Object, Object> dsMap = new HashMap();
    dsMap.put("datasource1", dataSource1());
    dsMap.put("datasource2", dataSource2());

    dynamicDataSource.setTargetDataSources(dsMap);
    return dynamicDataSource;
  }

  @Bean
  public PlatformTransactionManager transactionManager() {
    return new DataSourceTransactionManager(dynamicDataSource());
  }
}
