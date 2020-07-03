package com.aop.demo.game.model;

import java.io.Serializable;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "game")
public class Game implements Serializable {

  private static final long serialVersionUID = 85017204906501152L;


  private String id;


  /**
   * 名称
   */
  private String name;

}