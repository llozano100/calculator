package com.trend.calculator.rest.dto;

import lombok.Data;

@Data
public class RecordDto {

  private String result;
  private String operationType;
  private Double userBalance;
}
