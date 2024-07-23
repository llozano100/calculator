package com.trend.calculator.service.record;

import com.trend.calculator.model.Record;
import com.trend.calculator.rest.dto.RecordDto;
import com.trend.calculator.rest.dto.RecordRequest;
import com.trend.calculator.security.CustomUserDetails;
import org.springframework.stereotype.Service;

@Service
public class SubstractionOperationService implements OperationBasicService{

  @Override
  public RecordDto sendOperation(CustomUserDetails currentUser, RecordRequest recordRequest,
      Record record) {
    record.setOperationResponse(String.valueOf(Integer.parseInt(recordRequest.getParameter1())-Integer.parseInt(recordRequest.getParameter2())));
    RecordDto recordDto = new RecordDto();
    recordDto.setResult(record.getOperationResponse());

    return recordDto;
  }
}
