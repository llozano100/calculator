package com.trend.calculator.rest.dto;

import lombok.Data;

@Data
public class RecordRequest {

  private String parameter1;
  private String parameter2;
  private String operationType;
  private Integer amount;

}
