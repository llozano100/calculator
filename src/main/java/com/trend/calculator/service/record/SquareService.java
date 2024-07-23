package com.trend.calculator.service.record;

import com.trend.calculator.model.Record;
import com.trend.calculator.rest.dto.RecordDto;
import com.trend.calculator.rest.dto.RecordRequest;
import com.trend.calculator.security.CustomUserDetails;
import org.springframework.stereotype.Service;

@Service
public class SquareService implements OperationBasicService{

  @Override
  public RecordDto sendOperation(CustomUserDetails currentUser, RecordRequest recordRequest,
      Record record) {

    RecordDto recordDto = new RecordDto();
    if(recordRequest.getParameter2().isEmpty()) {
      record.setOperationResponse(String.valueOf(Math.sqrt(Double.parseDouble(recordRequest.getParameter1()))));
      recordDto.setResult(record.getOperationResponse());
    }
    return recordDto;
  }
}
