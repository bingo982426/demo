package com.aop.demo.config;

import java.util.HashMap;
import javax.sql.DataSource;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
public class DataSourceConfig extends BaseDruidConfig {

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
  public SqlSessionFactoryBean sqlSessionFactoryBean(
      @Qualifier("primaryDataSource") DataSource masterDataSource,
      @Qualifier("secondDataSource") DataSource secondDataSource) {
    SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
    sqlSessionFactoryBean.setDataSource(dynamicDataSource(masterDataSource, secondDataSource));
    return sqlSessionFactoryBean;
  }

  @Bean
  public PlatformTransactionManager transactionManager(
      @Qualifier("primaryDataSource") DataSource masterDataSource,
      @Qualifier("secondDataSource") DataSource secondDataSource) {
    return new DataSourceTransactionManager(dynamicDataSource(masterDataSource, secondDataSource));
  }

//  @Bean(name = "txAdvice")
//  public TransactionInterceptor getAdvisor(
//      @Qualifier("primaryDataSource") DataSource masterDataSource,
//      @Qualifier("secondDataSource") DataSource secondDataSource) {
//    Properties properties = new Properties();
//    properties.setProperty("get*", "PROPAGATION_NOT_SUPPORTED,-Exception,readOnly");
//    properties.setProperty("add*", "PROPAGATION_REQUIRED,-Exception");
//    properties.setProperty("save*", "PROPAGATION_REQUIRED,-Exception");
//    properties.setProperty("update*", "PROPAGATION_REQUIRED,-Exception");
//    properties.setProperty("delete*", "PROPAGATION_REQUIRED,-Exception");
//    properties.setProperty("delete*", "PROPAGATION_REQUIRED,-Exception");
//    return new TransactionInterceptor(transactionManager(masterDataSource, secondDataSource),
//        properties);
//  }
//
//  @Bean(name = "txProxy")
//  public BeanNameAutoProxyCreator txProxy() {
//    BeanNameAutoProxyCreator creator = new BeanNameAutoProxyCreator();
//    creator.setInterceptorNames("txAdvice");
//    creator.setBeanNames("*Service", "*ServiceImpl");
//    creator.setProxyTargetClass(true);
//    return creator;
//  }
}
