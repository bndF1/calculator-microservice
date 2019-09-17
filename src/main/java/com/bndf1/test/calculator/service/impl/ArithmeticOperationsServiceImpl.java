package com.bndf1.test.calculator.service.impl;

import com.bndf1.test.calculator.domain.dto.OperandDTO;
import com.bndf1.test.calculator.domain.dto.ResultDTO;
import com.bndf1.test.calculator.exceptions.ApiExceptions;
import com.bndf1.test.calculator.exceptions.OperandException;
import com.bndf1.test.calculator.service.ArithmeticOperationsService;
import io.corp.calculator.TracerImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;

import static com.bndf1.test.calculator.exceptions.ApiExceptions.FIRST_OPERAND_IS_NULL;
import static com.bndf1.test.calculator.exceptions.ApiExceptions.SECOND_OPERAND_IS_NULL;

@Service
public class ArithmeticOperationsServiceImpl implements ArithmeticOperationsService {

  private final TracerImpl tracer;

  @Autowired
  public ArithmeticOperationsServiceImpl(final TracerImpl tracer) {
    this.tracer = tracer;
  }

  @Override
  public ResultDTO add(final OperandDTO dto) {
    final BigDecimal firstOperand = getOperand(dto.getFirstOperand(), FIRST_OPERAND_IS_NULL);
    final BigDecimal secondOperand = getOperand(dto.getSecondOperand(), SECOND_OPERAND_IS_NULL);

    final BigDecimal result = firstOperand.add(secondOperand);
    traceResult(result);
    return ResultDTO.builder().result(result).build();
  }

  @Override
  public ResultDTO subtract(final OperandDTO dto) {
    final BigDecimal firstOperand = getOperand(dto.getFirstOperand(), FIRST_OPERAND_IS_NULL);
    final BigDecimal secondOperand = getOperand(dto.getSecondOperand(), SECOND_OPERAND_IS_NULL);

    final BigDecimal resultSubtract = firstOperand.subtract(secondOperand);
    traceResult(resultSubtract);
    return ResultDTO.builder().result(resultSubtract).build();
  }

  private BigDecimal getOperand(final BigDecimal operand, final ApiExceptions exception) {
    return Optional.ofNullable(operand).orElseThrow(() -> new OperandException(exception));
  }

  private void traceResult(final BigDecimal result) {
    this.tracer.trace(result);
  }
}
