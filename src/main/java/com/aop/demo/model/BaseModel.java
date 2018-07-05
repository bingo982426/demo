package com.aop.demo.model;

import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.Data;

@Data
public class BaseModel implements Serializable {

  private static final long serialVersionUID = -2864104564817210962L;
  private String id;
  private String createdBy;
  private String updatedBy;
  private LocalDateTime dtCreated;
  private LocalDateTime dtUpdated;
  private Integer version;

}
