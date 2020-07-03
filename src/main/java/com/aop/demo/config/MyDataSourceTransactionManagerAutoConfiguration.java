package com.aop.demo.config;

import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
public class MyDataSourceTransactionManagerAutoConfiguration extends
    DataSourceTransactionManagerAutoConfiguration {

  @Autowired
  private ApplicationContext appContext;

  /**
   * 自定义事务 MyBatis自动参与到spring事务管理中，无需额外配置，只要org.mybatis.spring.SqlSessionFactoryBean引用的数据源与DataSourceTransactionManager引用的数据源一致即可，否则事务管理会不起作用。
   *
   * @return
   */
  @Bean(name = "transactionManager")
  public DataSourceTransactionManager transactionManagers() {
    DataSource dynamicDataSource = (DataSource) appContext.getBean("dynamicDataSource");
    return new DataSourceTransactionManager(dynamicDataSource);
  }
}
