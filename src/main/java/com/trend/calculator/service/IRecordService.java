package com.trend.calculator.service;

import com.trend.calculator.model.Record;
import com.trend.calculator.rest.dto.RecordDto;
import com.trend.calculator.rest.dto.RecordRequest;
import com.trend.calculator.security.CustomUserDetails;
import java.util.List;

public interface IRecordService {

 /* RecordDto addOperation(CustomUserDetails currentUser,RecordRequest recordRequest);

  RecordDto generateRandomOperation(CustomUserDetails currentUser,String stringSize,String operationType,String amount);

  RecordDto squareOperation(CustomUserDetails currentUser,String stringSize,String amount);*/

  List<Record> getAllOperations(Integer page, Integer size,String email);

}
