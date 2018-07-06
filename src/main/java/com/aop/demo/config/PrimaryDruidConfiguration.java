package com.aop.demo.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;

@Configuration
//@MapperScan(basePackages = "com.aop.demo.mapper", sqlSessionFactoryRef = "primarySqlSessionFactory")
public class PrimaryDruidConfiguration extends BaseDruidConfig {

  private static final String PRIMARY_DATA_SOURCE_MAPPER = "classpath:mapper/*.xml";

  @Bean(name = "primaryDataSource")
  @Qualifier("primaryDataSource")
  @ConfigurationProperties("spring.datasource.druid.one")
  @Primary
  public DataSource dataSourceOne() {
    return DruidDataSourceBuilder.create().build();
  }

//  @Bean(name = "primarySqlSessionFactory")
//  @Qualifier("primarySqlSessionFactory")
//  @Primary
//  public SqlSessionFactory primarySqlSessionFactory(
//      @Qualifier("primaryDataSource") DataSource masterDataSource)
//      throws Exception {
//    final SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
//    sessionFactory.setDataSource(masterDataSource);
//    sessionFactory.setMapperLocations(new PathMatchingResourcePatternResolver()
//        .getResources(PrimaryDruidConfiguration.PRIMARY_DATA_SOURCE_MAPPER));
//    return sessionFactory.getObject();
//  }
//
//  @Bean(name = "primaryTransactionManager")
//  @Primary
//  public DataSourceTransactionManager primaryTransactionManager() {
//    DataSourceTransactionManager dataSourceTransactionManager = new DataSourceTransactionManager(
//        dataSourceOne());
//
//    dataSourceTransactionManager.afterPropertiesSet();
//    return dataSourceTransactionManager;
//  }
//
//  @Bean(name = "primaryTxAdvice")
//  @Primary
//  public TransactionInterceptor getAdvisor() {
//    Properties properties = new Properties();
//    properties.setProperty("get*", "PROPAGATION_NOT_SUPPORTED,-Exception,readOnly");
//    properties.setProperty("add*", "PROPAGATION_REQUIRED,-Exception");
//    properties.setProperty("save*", "PROPAGATION_REQUIRED,-Exception");
//    properties.setProperty("update*", "PROPAGATION_REQUIRED,-Exception");
//    properties.setProperty("delete*", "PROPAGATION_REQUIRED,-Exception");
//    properties.setProperty("delete*", "PROPAGATION_REQUIRED,-Exception");
//    return new TransactionInterceptor(primaryTransactionManager(), properties);
//  }
//
//  @Bean(name = "primaryTxProxy")
//  @Primary
//  public BeanNameAutoProxyCreator txProxy() {
//    BeanNameAutoProxyCreator creator = new BeanNameAutoProxyCreator();
//    creator.setInterceptorNames("primaryTxAdvice");
//    creator.setBeanNames("*Service", "*ServiceImpl");
//    creator.setProxyTargetClass(true);
//    return creator;
//  }
}
