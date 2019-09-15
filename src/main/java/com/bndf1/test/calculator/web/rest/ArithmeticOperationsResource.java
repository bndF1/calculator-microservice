package com.bndf1.test.calculator.web.rest;

import com.bndf1.test.calculator.domain.dto.OperandDTO;
import com.bndf1.test.calculator.domain.dto.ResultDTO;
import com.bndf1.test.calculator.service.ArithmeticOperationsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URISyntaxException;

@RestController
@RequestMapping("/api")
public class ArithmeticOperationsResource {

  private final ArithmeticOperationsService arithmeticOperationsService;
  private final Logger log = LoggerFactory.getLogger(ArithmeticOperationsResource.class);

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
   * @throws URISyntaxException if the Location URI syntax is incorrect.
   */
  @PostMapping("/add")
  public ResponseEntity<ResultDTO> add(@RequestBody final OperandDTO operandDTO)
      throws URISyntaxException {
    log.debug("REST request to perform the add operation : {}", operandDTO);
    final ResultDTO resultDTO = this.arithmeticOperationsService.add(operandDTO);
    return ResponseEntity.ok().body(resultDTO);
  }
}
