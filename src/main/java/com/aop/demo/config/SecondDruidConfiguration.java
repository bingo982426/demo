package com.aop.demo.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;

@Configuration
//@MapperScan(basePackages = "com.aop.demo.mapper", sqlSessionFactoryRef = "secondSqlSessionFactory")
public class SecondDruidConfiguration extends BaseDruidConfig {

  static final String SECOND_DATA_SOURCE_MAPPER = "classpath:mapper/*.xml";


  @Bean(name = "secondDataSource")
  @Qualifier("secondDataSource")
  @ConfigurationProperties("spring.datasource.druid.two")
  public DataSource dataSourceTwo() {
    return DruidDataSourceBuilder.create().build();
  }

//  @Bean(name = "secondSqlSessionFactory")
//  @Qualifier("secondSqlSessionFactory")
//  public SqlSessionFactory secondSqlSessionFactory(
//      @Qualifier("secondDataSource") DataSource secondDataSource)
//      throws Exception {
//    final SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
//    sessionFactory.setDataSource(secondDataSource);
//    sessionFactory.setMapperLocations(new PathMatchingResourcePatternResolver()
//        .getResources(SecondDruidConfiguration.SECOND_DATA_SOURCE_MAPPER));
//    return sessionFactory.getObject();
//  }
//
//
//  @Bean(name = "secondTransactionManager")
//  public DataSourceTransactionManager secondTransactionManager() {
//    return new DataSourceTransactionManager(dataSourceTwo());
//  }
//
//  @Bean(name = "secondTxAdvice")
//  public TransactionInterceptor getAdvisor() {
//    Properties properties = new Properties();
//    properties.setProperty("get*", "PROPAGATION_NOT_SUPPORTED,-Exception,readOnly");
//    properties.setProperty("add*", "PROPAGATION_REQUIRED,-Exception");
//    properties.setProperty("save*", "PROPAGATION_REQUIRED,-Exception");
//    properties.setProperty("update*", "PROPAGATION_REQUIRED,-Exception");
//    properties.setProperty("delete*", "PROPAGATION_REQUIRED,-Exception");
//    properties.setProperty("delete*", "PROPAGATION_REQUIRED,-Exception");
//    return new TransactionInterceptor(secondTransactionManager(), properties);
//  }
//
//  @Bean(name = "secondTxProxy")
//  public BeanNameAutoProxyCreator txProxy() {
//    BeanNameAutoProxyCreator creator = new BeanNameAutoProxyCreator();
//    creator.setInterceptorNames("secondTxAdvice");
//    creator.setBeanNames("*Service", "*ServiceImpl");
//    creator.setProxyTargetClass(true);
//    return creator;
//  }
}
