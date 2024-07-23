package com.trend.calculator.service.record;

import com.trend.calculator.model.Record;
import com.trend.calculator.rest.dto.RecordDto;
import com.trend.calculator.rest.dto.RecordRequest;
import com.trend.calculator.security.CustomUserDetails;
import java.util.Map;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class RandomService implements OperationBasicService{

  @Value("${app.random}")
  private String randomUrl ;

  private final RestTemplate restTemplate;

  public RandomService(RestTemplate restTemplate) {
    this.restTemplate = restTemplate;
  }

  @Override
  public RecordDto sendOperation(CustomUserDetails currentUser, RecordRequest recordRequest, Record record) {

    RecordDto recordDto = new RecordDto();
    ResponseEntity<String> responseEntity = restTemplate.getForEntity(randomUrl, String.class, Map.of("num", recordRequest.getParameter1()));
    if(responseEntity.getStatusCode().is2xxSuccessful()){
      record.setOperationResponse(String.valueOf(responseEntity.getBody()));
      recordDto.setResult(record.getOperationResponse());
    }

    return recordDto;
  }
}
