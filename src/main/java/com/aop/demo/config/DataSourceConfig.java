package com.aop.demo.config;

import java.util.HashMap;
import java.util.Properties;

import javax.sql.DataSource;

import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.aop.framework.autoproxy.BeanNameAutoProxyCreator;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.interceptor.TransactionInterceptor;

@Configuration
public class DataSourceConfig extends BaseDruidConfig {

  @Primary
  @Bean(name = "dynamicDataSource")
  public DataSource dynamicDataSource(@Qualifier("primaryDataSource") DataSource masterDataSource,
      @Qualifier("secondDataSource") DataSource secondDataSource) {
    DynamicDataSource dynamicDataSource = new DynamicDataSource();
    dynamicDataSource.setDefaultTargetDataSource(masterDataSource);
    HashMap dsMap = new HashMap();
    dsMap.put("primaryDataSource", masterDataSource);
    dsMap.put("secondDataSource", secondDataSource);

    dynamicDataSource.setTargetDataSources(dsMap);
    return dynamicDataSource;
  }

  @Bean
  public SqlSessionFactoryBean sqlSessionFactoryBean(@Qualifier("primaryDataSource") DataSource masterDataSource,
      @Qualifier("secondDataSource") DataSource secondDataSource) {
    SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
    // 配置数据源，此处配置为关键配置，如果没有将 dynamicDataSource 作为数据源则不能实现切换
    sqlSessionFactoryBean.setDataSource(dynamicDataSource(masterDataSource, secondDataSource));
    return sqlSessionFactoryBean;
  }

  @Bean
  public PlatformTransactionManager transactionManager(@Qualifier("primaryDataSource") DataSource masterDataSource,
      @Qualifier("secondDataSource") DataSource secondDataSource) {
    return new DataSourceTransactionManager(dynamicDataSource(masterDataSource, secondDataSource));
  }

  @Bean(name = "txAdvice")
  public TransactionInterceptor getAdvisor(@Qualifier("primaryDataSource") DataSource masterDataSource,
      @Qualifier("secondDataSource") DataSource secondDataSource) {
    Properties properties = new Properties();
    properties.setProperty("get*", "PROPAGATION_NOT_SUPPORTED,-Exception,readOnly");
    properties.setProperty("add*", "PROPAGATION_REQUIRED,-Exception");
    properties.setProperty("save*", "PROPAGATION_REQUIRED,-Exception");
    properties.setProperty("update*", "PROPAGATION_REQUIRED,-Exception");
    properties.setProperty("delete*", "PROPAGATION_REQUIRED,-Exception");
    properties.setProperty("delete*", "PROPAGATION_REQUIRED,-Exception");
    return new TransactionInterceptor(transactionManager(masterDataSource, secondDataSource), properties);
  }

  @Bean(name = "txProxy")
  @Primary
  public BeanNameAutoProxyCreator txProxy() {
    BeanNameAutoProxyCreator creator = new BeanNameAutoProxyCreator();
    creator.setInterceptorNames("txAdvice");
    creator.setBeanNames("*Service", "*ServiceImpl");
    creator.setProxyTargetClass(true);
    return creator;
  }
}
