package com.bndf1.test.calculator.web.rest;

import com.bndf1.test.calculator.domain.dto.OperandDTO;
import com.bndf1.test.calculator.domain.dto.ResultDTO;
import com.bndf1.test.calculator.exceptions.OperandException;
import com.bndf1.test.calculator.service.ArithmeticOperationsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class ArithmeticOperationsResource {

  private final ArithmeticOperationsService arithmeticOperationsService;
  private final Logger log = LoggerFactory.getLogger(ArithmeticOperationsResource.class);

  @Autowired
  public ArithmeticOperationsResource(
      final ArithmeticOperationsService arithmeticOperationsService) {
    this.arithmeticOperationsService = arithmeticOperationsService;
  }

  /**
   * {@code POST /add} : Perform add operation.
   *
   * @param operandDTO the DTO representing the two numbers to sum.
   * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the resultDTO, or
   *     with status {@code 400 (Bad Request)} if something wrong.
   */
  @PostMapping("/add")
  public ResponseEntity<ResultDTO> add(@RequestBody final OperandDTO operandDTO) {
    log.debug("REST request to perform the add operation : {}", operandDTO);
    if (operandDTO != null) {
      final ResultDTO resultDTO = this.arithmeticOperationsService.add(operandDTO);
      return ResponseEntity.ok().body(resultDTO);
    }
    return ResponseEntity.badRequest().build();
  }

  @ExceptionHandler({ArithmeticException.class, OperandException.class})
  public ResponseEntity<String> handleException(final RuntimeException exception) {
    return new ResponseEntity<>(exception.getLocalizedMessage(), HttpStatus.BAD_REQUEST);
  }

  @PostMapping("/subtract")
  public ResponseEntity<ResultDTO> subtract(@RequestBody final OperandDTO operandDTO) {
    if (operandDTO != null) {
      final ResultDTO resultDTO = this.arithmeticOperationsService.subtract(operandDTO);
      return ResponseEntity.ok(resultDTO);
    }
    return ResponseEntity.badRequest().build();
  }
}
