package com.trend.calculator.service.record;

import com.trend.calculator.exception.ServerErrorException;
import com.trend.calculator.exception.UserNotFoundException;
import com.trend.calculator.model.Operation;
import com.trend.calculator.model.Record;
import com.trend.calculator.repository.IOperationRepository;
import com.trend.calculator.repository.IRecordRepository;
import com.trend.calculator.rest.dto.RecordDto;
import com.trend.calculator.rest.dto.RecordRequest;
import com.trend.calculator.security.CustomUserDetails;
import java.util.Optional;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@Primary
@AllArgsConstructor
@NoArgsConstructor(force = true)
public class DefaultOperationServiceImpl implements OperationBasicService{

  private final OperationFactory operationFactory;
  private final IRecordRepository iRecordRepository;
  private final IOperationRepository iOperationRepository;

  private Record getOperationData(CustomUserDetails currentUser, RecordRequest recordRequest){

    Operation operation;
    Record record ;

    record = iRecordRepository.findByRecordUsernameAndOperationType(currentUser.getEmail(),
        recordRequest.getOperationType());

    if(Optional.ofNullable(record).isEmpty()) {
      operation =  iOperationRepository.findByTypeAndOperationsEmail(recordRequest.getOperationType(),currentUser.getEmail());
      if(Optional.ofNullable(operation).isPresent()){
        record =new Record();
        record.setRecord(operation.getOperations());

      }else{
        throw new UserNotFoundException(
            String.format("User with username %s not found", currentUser.getEmail()));
      }

    }else{
      operation = record.getOperation();
      record.setRecord(record.getRecord());
    }
    record.setOperation(operation);

    return record;

  }


  @Override
  public RecordDto sendOperation(CustomUserDetails currentUser, RecordRequest recordRequest,Record record) {
    record =  getOperationData(currentUser,recordRequest);
    operationFactory.getOperationService(
            OperationBasicAction.valueOf(recordRequest.getOperationType()))
        .sendOperation(currentUser, recordRequest, record);
    return saveData(record,recordRequest);
  }

  private RecordDto saveData(Record record,RecordRequest recordRequest){

    RecordDto recordDto = new RecordDto();
    try{
      record.setAmount(recordRequest.getAmount());

      record.setUserBalance(record.getOperation().getCost()-Integer.parseInt(
          String.valueOf(recordRequest.getAmount())));

      iRecordRepository.saveAndFlush(record);

      recordDto.setOperationType(recordRequest.getOperationType());
      recordDto.setResult(record.getOperationResponse());
      recordDto.setUserBalance(record.getUserBalance());
    }catch(Exception ex){
      throw new ServerErrorException(
          String.format("User with username %s not found", record.getOperation().getOperations().getUsername()));
    }

    return recordDto;
  }
}
