package com.aop.demo.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import javax.sql.DataSource;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import tk.mybatis.spring.annotation.MapperScan;


@Configuration
@MapperScan(value = "com.aop.demo.game.mapper", sqlSessionFactoryRef = "primarySqlSessionFactory")
public class PrimaryDataSourceConfig {

  @Primary
  @Bean(name = "primaryDataSource")
  @Qualifier("primaryDataSource")
  @ConfigurationProperties("spring.datasource.druid.game")
  public DruidDataSource primaryDataSource() {
    return DruidDataSourceBuilder.create().build();
  }


  @Bean(name = "dynamicDataSource")
  public DataSource dynamicDataSource(
      @Qualifier("primaryDataSource") DruidDataSource primaryDataSource,
      @Qualifier("secondDataSource") DruidDataSource secondDataSource) {
    DynamicDataSource dynamicDataSource = new DynamicDataSource();
    dynamicDataSource.setDefaultTargetDataSource(primaryDataSource());
    Map<Object, Object> dsMap = new HashMap<>();
    dsMap.put("primaryDataSource", primaryDataSource);
    dsMap.put("secondDataSource", secondDataSource);
    dynamicDataSource.setTargetDataSources(dsMap);
    return dynamicDataSource;
  }

  @Primary
  @Bean(name = "primarySqlSessionFactory")
  @Qualifier("primarySqlSessionFactory")
  public SqlSessionFactoryBean primarySqlSessionFactory(
      @Qualifier("dynamicDataSource") DataSource dynamicDataSource) throws IOException {
    SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
    sqlSessionFactoryBean.setDataSource(dynamicDataSource);
    sqlSessionFactoryBean.setMapperLocations(
        new PathMatchingResourcePatternResolver()
            .getResources("classpath*:mapper/game/*Mapper.xml"));
    return sqlSessionFactoryBean;
  }

//  @Bean
//  public SqlSessionFactoryBean sqlSessionFactoryBean() {
//    SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
//    // 配置数据源，此处配置为关键配置，如果没有将 dynamicDataSource 作为数据源则不能实现切换
//    sqlSessionFactoryBean.setDataSource(dynamicDataSource());
//    return sqlSessionFactoryBean;
//  }

  @Bean
  public PlatformTransactionManager transactionManager(
      @Qualifier("dynamicDataSource") DataSource dynamicDataSource) {
    return new DataSourceTransactionManager(dynamicDataSource);
  }

//  @Bean(name = "txAdvice")
//  public TransactionInterceptor getAdvisor() {
//    TransactionInterceptor transactionInterceptor = new TransactionInterceptor();
//    Properties properties = new Properties();
//    properties.setProperty("get*", "PROPAGATION_NOT_SUPPORTED,-Exception,readOnly");
//    properties.setProperty("find*", "PROPAGATION_NOT_SUPPORTED,-Exception,readOnly");
//    properties.setProperty("add*", "PROPAGATION_REQUIRED,-Exception");
//    properties.setProperty("save*", "PROPAGATION_REQUIRED,-Exception");
//    properties.setProperty("update*", "PROPAGATION_REQUIRED,-Exception");
//    properties.setProperty("delete*", "PROPAGATION_REQUIRED,-Exception");
//    properties.setProperty("gen*", "PROPAGATION_REQUIRED,-Exception");
//    transactionInterceptor.setTransactionAttributes(properties);
//    return transactionInterceptor;
//  }

//  @Bean(name = "txProxy")
//  public BeanNameAutoProxyCreator txProxy() {
//  public AnnotationAwareAspectJAutoProxyCreator txProxy() {
//    AnnotationAwareAspectJAutoProxyCreator transactionAutoProxy = new AnnotationAwareAspectJAutoProxyCreator();
//    transactionAutoProxy.setProxyTargetClass(true);
//    transactionAutoProxy
//        .setIncludePatterns(
//            Arrays.asList("execution(public com.aop.demo.game.service.impl..*(..))",
//                "execution(public com.aop.demo.competition.service.impl..*(..))"));
//    transactionAutoProxy.setInterceptorNames("txAdvice");
//    transactionAutoProxy.setOrder(99);
//    return transactionAutoProxy;
//    BeanNameAutoProxyCreator creator = new BeanNameAutoProxyCreator();
//    creator.setInterceptorNames("txAdvice");
//    creator.setBeanNames("*Service", "*ServiceImpl");
//    creator.setProxyTargetClass(true);
//    creator.setOrder(99);
//    return creator;
//  }

//  @Bean(name = "sqlSessionTemplate")
//  public CustomSqlSessionTemplate sqlSessionTemplate(
//      @Qualifier("primarySqlSessionFactory") SqlSessionFactory primaryFactory,
//      @Qualifier("secondSqlSessionFactory") SqlSessionFactory secondFactory,
//      @Qualifier("thirdSqlSessionFactory") SqlSessionFactory thirdFactory) throws Exception {
//    Map<Object, SqlSessionFactory> sqlSessionFactoryMap = new HashMap<>();
//    sqlSessionFactoryMap.put("primaryDataSource", primaryFactory);
//    sqlSessionFactoryMap.put("secondDataSource", secondFactory);
//    sqlSessionFactoryMap.put("thirdDataSource", thirdFactory);
//    CustomSqlSessionTemplate customSqlSessionTemplate = new CustomSqlSessionTemplate(
//        primaryFactory);
//    customSqlSessionTemplate.setTargetSqlSessionFactoryMap(sqlSessionFactoryMap);
//    customSqlSessionTemplate.setDefaultTargetSqlSessionFactory(primaryFactory);
//    return customSqlSessionTemplate;
//  }

}
