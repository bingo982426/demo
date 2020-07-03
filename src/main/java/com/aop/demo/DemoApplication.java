package com.aop.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

//@Import({DynamicDataSource.class})
@SpringBootApplication(
//    exclude = {DataSourceAutoConfiguration.class}
)
//@MapperScan(basePackages = {"com.aop.demo.*.mapper"})
@EnableTransactionManagement(proxyTargetClass = true)
public class DemoApplication {

  public static void main(String[] args) {
    SpringApplication.run(DemoApplication.class, args);
  }
}
