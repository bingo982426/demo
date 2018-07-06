package com.aop.demo.config;

import lombok.extern.slf4j.Slf4j;

@Slf4j
class DataSourceContextHolder {

  private static final ThreadLocal<String> contextHolder = new ThreadLocal<>();

  static void setDB(String dbType) {
    log.info("切换到[{}]数据源", dbType);
    contextHolder.set(dbType);
  }

  static String getDB() {
    return (contextHolder.get());
  }

  static void clearDB() {
    contextHolder.remove();
  }
}