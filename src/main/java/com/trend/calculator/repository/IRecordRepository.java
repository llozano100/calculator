package com.trend.calculator.repository;

import com.trend.calculator.model.Record;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IRecordRepository extends JpaRepository<Record,Long> {

  Record findByRecordUsernameAndOperationType(String userEmail,String operationType);

  Page<Record> findAllByRecordEmail(String emailUser, Pageable pageable);

}
