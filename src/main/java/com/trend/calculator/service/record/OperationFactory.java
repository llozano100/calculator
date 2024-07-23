package com.trend.calculator.service.record;

import java.util.AbstractMap;
import java.util.Map;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class OperationFactory {

  private final AddOperationService addOperationService;
  private final SubstractionOperationService substractionOperationService;
  private final MultiOperationService multiOperationService;
  private final DivisionOperationService divisionOperationService;
  private final RandomService randomService;
  private final SquareService squareService;


  public OperationBasicService getOperationService(OperationBasicAction operationBasicAction) {
    return getMapService().get(operationBasicAction);
  }

  private Map<OperationBasicAction, OperationBasicService> getMapService() {
    return Map.ofEntries(
        new AbstractMap.SimpleEntry<>(OperationBasicAction.ADD, addOperationService),
        new AbstractMap.SimpleEntry<>(OperationBasicAction.SUBSTRACTION, substractionOperationService),
        new AbstractMap.SimpleEntry<>(OperationBasicAction.MULTIPLICATION, multiOperationService),
        new AbstractMap.SimpleEntry<>(OperationBasicAction.DIVISION, divisionOperationService),
        new AbstractMap.SimpleEntry<>(OperationBasicAction.RANDOM, randomService),
        new AbstractMap.SimpleEntry<>(OperationBasicAction.SQUARE, squareService));
  }

}
