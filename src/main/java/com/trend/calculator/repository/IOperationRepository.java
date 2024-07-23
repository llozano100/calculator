package com.trend.calculator.repository;

import com.trend.calculator.model.Operation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IOperationRepository extends JpaRepository<Operation,Long> {

  Operation findByTypeAndOperationsEmail(String type,String userEmail);

}
