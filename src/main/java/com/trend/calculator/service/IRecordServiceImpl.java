package com.trend.calculator.service;

import com.trend.calculator.exception.ResourceNotFoundException;
import com.trend.calculator.model.Record;
import com.trend.calculator.repository.IRecordRepository;
import java.util.ArrayList;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class IRecordServiceImpl implements IRecordService {
  private final IRecordRepository iRecordRepository;

  public IRecordServiceImpl(IRecordRepository iRecordRepository) {
    this.iRecordRepository = iRecordRepository;
  }

  @Override
  public List<Record> getAllOperations(Integer page, Integer size, String email) {
    List<Record> recordDtoList = new ArrayList<>();
    Pageable pageable = PageRequest.of(page, size);
    Page<Record> pageUser;
    try {

      if (email == null) {
        pageUser = iRecordRepository.findAll(pageable);
      } else {
        pageUser = iRecordRepository.findAllByRecordEmail(email, pageable);
      }

      pageUser.getContent();
      pageUser.getContent().forEach(record -> {
        Record record1 = new Record();
        record1.setOperation(record.getOperation());
        record1.setRecord(record.getRecord());

        recordDtoList.add(record1);

      });

    } catch (ResourceNotFoundException ex) {
      throw new ResourceNotFoundException(
          String.format("Resource is %s not found", ex.getMessage()));
    }
    return recordDtoList;
  }

}
