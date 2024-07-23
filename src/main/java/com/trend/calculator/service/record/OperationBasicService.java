package com.trend.calculator.service.record;

import com.trend.calculator.model.Record;
import com.trend.calculator.rest.dto.RecordDto;
import com.trend.calculator.rest.dto.RecordRequest;
import com.trend.calculator.security.CustomUserDetails;

public interface OperationBasicService {

  RecordDto sendOperation(CustomUserDetails currentUser, RecordRequest recordRequest, Record record);

}
