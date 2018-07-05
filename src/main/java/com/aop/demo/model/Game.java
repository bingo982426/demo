package com.aop.demo.model;

import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
public @Data
class Game extends BaseModel implements Serializable {

  private static final long serialVersionUID = 85017204906501152L;
  /**
   * 名称
   */
  private String name;

  /**
   * 简称
   */
  private String sname;

  /**
   * 英文名称
   */
  private String ename;

}