package com.trend.calculator.rest;

import static com.trend.calculator.config.SwaggerConfig.BEARER_KEY_SECURITY_SCHEME;

import com.trend.calculator.model.Record;
import com.trend.calculator.rest.dto.RecordDto;
import com.trend.calculator.rest.dto.RecordRequest;
import com.trend.calculator.security.CustomUserDetails;
import com.trend.calculator.service.IRecordService;
import com.trend.calculator.service.record.OperationBasicService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.BadRequestException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/v1/calculator/record")
public class RecordController {
  private final  IRecordService iRecordService;
  private final OperationBasicService operationBasicService;

  @Operation(security = {@SecurityRequirement(name = BEARER_KEY_SECURITY_SCHEME)})
  @PostMapping("/operation")
  public ResponseEntity<?> add(@AuthenticationPrincipal CustomUserDetails currentUser,
                               @Valid@RequestBody RecordRequest recordRequest) {
    try {
      RecordDto recordDto = operationBasicService.sendOperation(currentUser,recordRequest,null);
      return ResponseEntity.ok(recordDto);
    } catch (Exception e) {
      return new ResponseEntity<>(recordRequest.getOperationType(), HttpStatus.BAD_REQUEST);
    }
  }
  @Operation(security = {@SecurityRequirement(name = BEARER_KEY_SECURITY_SCHEME)})
  @GetMapping("records")
  public ResponseEntity<?> getAllPageable(
      @RequestParam(required = false) String email,
      @RequestParam(defaultValue = "0") Integer page,
      @RequestParam(defaultValue = "5") Integer size) throws BadRequestException {

    List<Record> recordList = iRecordService.getAllOperations(page,size,email);
    if (Optional.ofNullable(recordList).isPresent()) {
      return ResponseEntity.status(HttpStatus.OK)
          .body(recordList);
    } else {
      throw new BadRequestException("BAD_REQUEST_ERROR_MESSAGE");
    }
  }

}
