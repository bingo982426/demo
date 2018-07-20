package com.aop.demo.config;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import javax.sql.DataSource;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.aop.aspectj.annotation.AnnotationAwareAspectJAutoProxyCreator;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.interceptor.TransactionInterceptor;


@Configuration
public class DataSourceConfig {

  @Bean(name = "primaryDataSource")
  @Qualifier("primaryDataSource")
  @ConfigurationProperties("spring.datasource.hikari.one")
  public DataSource primaryDataSource() {
    return DataSourceBuilder.create().build();
  }

  @Bean(name = "secondDataSource")
  @Qualifier("secondDataSource")
  @ConfigurationProperties("spring.datasource.hikari.two")
  public DataSource secondDataSource() {
    return DataSourceBuilder.create().build();
  }

  @Primary
  @Bean(name = "dynamicDataSource")
  public DataSource dynamicDataSource() {
    DynamicDataSource dynamicDataSource = new DynamicDataSource();
    dynamicDataSource.setDefaultTargetDataSource(primaryDataSource());
    Map<Object, Object> dsMap = new HashMap<>();
    dsMap.put("primaryDataSource", primaryDataSource());
    dsMap.put("secondDataSource", secondDataSource());
    dynamicDataSource.setTargetDataSources(dsMap);
    return dynamicDataSource;
  }

  @Bean
  public SqlSessionFactoryBean sqlSessionFactoryBean() {
    SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
    // 配置数据源，此处配置为关键配置，如果没有将 dynamicDataSource 作为数据源则不能实现切换
    sqlSessionFactoryBean.setDataSource(dynamicDataSource());
    return sqlSessionFactoryBean;
  }

  @Bean
  public PlatformTransactionManager transactionManager() {
    return new DataSourceTransactionManager(dynamicDataSource());
  }

  @Bean(name = "txAdvice")
  public TransactionInterceptor getAdvisor() {
    TransactionInterceptor transactionInterceptor = new TransactionInterceptor();
    Properties properties = new Properties();
    properties.setProperty("get*", "PROPAGATION_NOT_SUPPORTED,-Exception,readOnly");
    properties.setProperty("find*", "PROPAGATION_NOT_SUPPORTED,-Exception,readOnly");
    properties.setProperty("add*", "PROPAGATION_REQUIRED,-Exception");
    properties.setProperty("save*", "PROPAGATION_REQUIRED,-Exception");
    properties.setProperty("update*", "PROPAGATION_REQUIRED,-Exception");
    properties.setProperty("delete*", "PROPAGATION_REQUIRED,-Exception");
    properties.setProperty("gen*", "PROPAGATION_REQUIRED,-Exception");
    transactionInterceptor.setTransactionAttributes(properties);
    return transactionInterceptor;
  }

  @Bean(name = "txProxy")
  public AnnotationAwareAspectJAutoProxyCreator txProxy() {
    AnnotationAwareAspectJAutoProxyCreator transactionAutoProxy = new AnnotationAwareAspectJAutoProxyCreator();
    transactionAutoProxy.setProxyTargetClass(true);
    transactionAutoProxy
        .setIncludePatterns(
            Collections.singletonList("execution(public com.aop.demo.service.impl..*(..))"));
    transactionAutoProxy.setInterceptorNames("txAdvice");
    transactionAutoProxy.setOrder(99);
    return transactionAutoProxy;
  }

}
