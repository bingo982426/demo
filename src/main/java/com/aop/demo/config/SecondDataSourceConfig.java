package com.aop.demo.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
import java.io.IOException;
import javax.sql.DataSource;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import tk.mybatis.spring.annotation.MapperScan;


@Configuration
@MapperScan(value = "com.aop.demo.competition.mapper", sqlSessionFactoryRef = "secondSqlSessionFactory")
public class SecondDataSourceConfig {

  @Autowired
  ConfigurableApplicationContext applicationContext;


  @Bean(name = "secondDataSource")
  @Qualifier("secondDataSource")
  @ConfigurationProperties("spring.datasource.druid.competition")
  public DruidDataSource secondDataSource() {
    return DruidDataSourceBuilder.create().build();
  }

  @Bean(name = "secondSqlSessionFactory")
  @Qualifier("secondSqlSessionFactory")
  public SqlSessionFactoryBean secondSqlSessionFactory(
      @Qualifier("dynamicDataSource") DataSource dynamicDataSource) throws IOException {
    SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
    sqlSessionFactoryBean.setDataSource(dynamicDataSource);
    sqlSessionFactoryBean.setMapperLocations(
        new PathMatchingResourcePatternResolver()
            .getResources("classpath*:mapper/competition/*Mapper.xml"));
    return sqlSessionFactoryBean;
  }
//  @Bean(name = "secondSqlSessionFactory")
//  public SqlSessionFactory sqlSessionFactory(
//      @Qualifier("secondDataSource") DruidDataSource secondDataSource) throws Exception {
//    SqlSessionFactoryBean fb = new SqlSessionFactoryBean();
//    fb.setDataSource(secondDataSource);
//    fb.setVfs(SpringBootVFS.class);
//    fb.setMapperLocations(new PathMatchingResourcePatternResolver()
//        .getResources("classpath*:mapper/match/*Mapper.xml"));
//    return fb.getObject();
//  }

//  @Bean("secondTransactionManager")
//  public DataSourceTransactionManager transactionManager(
//      @Qualifier("secondDataSource") DruidDataSource secondDataSource) {
//    return new DataSourceTransactionManager(secondDataSource);
//  }

//  @Bean(name = "secondSqlSessionTemplate")
//  public SqlSessionTemplate sqlSessionTemplate(
//      @Qualifier("secondSqlSessionFactory") SqlSessionFactory secondSqlSessionFactory) {
//    return new SqlSessionTemplate(secondSqlSessionFactory);
//  }
//
//  @Bean(name = "secondSqlSessionFactory")
//  @Qualifier("secondSqlSessionFactory")
//  public SqlSessionFactoryBean secondSqlSessionFactory(
//      @Qualifier("secondDataSource") DruidDataSource druidDataSource) throws IOException {
//    SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
//    sqlSessionFactoryBean.setDataSource(druidDataSource);
//    sqlSessionFactoryBean.setMapperLocations(
//        new PathMatchingResourcePatternResolver()
//            .getResources("classpath*:mapper/match/*Mapper.xml"));
//    return sqlSessionFactoryBean;
//  }
//
//  @Bean(name = "secondTransactionManager")
//  public PlatformTransactionManager transactionManager() {
//    return new DataSourceTransactionManager(secondDataSource());
//  }
}
