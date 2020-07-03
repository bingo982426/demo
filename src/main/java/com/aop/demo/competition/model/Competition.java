package com.aop.demo.competition.model;

import java.io.Serializable;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "competition")
public class Competition implements Serializable {

  private static final long serialVersionUID = 85017204906501132L;


  private String id;


  /**
   * 名称
   */
  private String name;

}