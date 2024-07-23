package com.trend.calculator.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;

import com.trend.calculator.model.Record;
import com.trend.calculator.repository.IOperationRepository;
import com.trend.calculator.repository.IRecordRepository;
import com.trend.calculator.rest.dto.RecordDto;
import com.trend.calculator.rest.dto.RecordRequest;
import com.trend.calculator.security.CustomUserDetails;
import com.trend.calculator.service.record.AddOperationService;
import com.trend.calculator.service.record.DivisionOperationService;
import com.trend.calculator.service.record.MultiOperationService;
import com.trend.calculator.service.record.OperationBasicService;
import com.trend.calculator.service.record.OperationFactory;
import com.trend.calculator.service.record.SubstractionOperationService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;


@ExtendWith(MockitoExtension.class)
public class FactoryServiceTest {

  @InjectMocks
  private OperationBasicService substraction = new SubstractionOperationService();
  @InjectMocks
  private OperationBasicService operationBasicService = new AddOperationService();

  @InjectMocks
  private OperationBasicService multi = new MultiOperationService();

  @InjectMocks
  private OperationBasicService division = new DivisionOperationService();

  @BeforeAll
  public static void beforeAll() {
    MockitoAnnotations.openMocks(FactoryServiceTest.class);
  }
  @DisplayName("JUnit test for adding operation")
  @Test
  public void addOperationOK() {

    CustomUserDetails currentUser = getUser();
    RecordRequest recordRequest = getRequest("ADD");
    Record record = new Record();
    record.setId(1L);
    record.setAmount(12);

    RecordDto recordDto = operationBasicService.sendOperation(currentUser,recordRequest,record);

    assertThat(recordDto).isNotNull();


  }

  @DisplayName("JUnit test for substraction operation")
  @Test
  public void substractionOperationOK() {

    CustomUserDetails currentUser = getUser();
    RecordRequest recordRequest = getRequest("SUBSTRACTION");
    Record record = new Record();
    record.setId(1L);
    record.setAmount(12);

    RecordDto recordDto = substraction.sendOperation(currentUser,recordRequest,record);
    assertThat(recordDto).isNotNull();

  }

  @DisplayName("JUnit test for multiplication operation")
  @Test
  public void multiplicationOperationOK() {

    CustomUserDetails currentUser = getUser();
    RecordRequest recordRequest = getRequest("MULTIPLICATION");
    Record record = new Record();
    record.setId(1L);
    record.setAmount(12);

    RecordDto recordDto = multi.sendOperation(currentUser,recordRequest,record);
    assertThat(recordDto).isNotNull();

  }

  @DisplayName("JUnit test for division operation")
  @Test
  public void divisionOperationOK() {

    CustomUserDetails currentUser = getUser();
    RecordRequest recordRequest = getRequest("DIVISION");
    Record record = new Record();
    record.setId(1L);
    record.setAmount(12);

    RecordDto recordDto = division.sendOperation(currentUser,recordRequest,record);
    assertThat(recordDto).isNotNull();

  }

  private CustomUserDetails getUser()
  {
    CustomUserDetails currentUser = new CustomUserDetails();
    currentUser.setId(1L);
    currentUser.setUsername("jlozano");
    currentUser.setName("jlozano");
    currentUser.setPassword("12345");
    currentUser.setEmail("jllozano.100@gmail.com");
    return currentUser;
  }

  private RecordRequest getRequest(String type ){
    RecordRequest recordRequest = new RecordRequest();
    recordRequest.setParameter1(String.valueOf(10));
    recordRequest.setParameter2(String.valueOf(10));
    recordRequest.setOperationType(type);
    return recordRequest;
  }

}
