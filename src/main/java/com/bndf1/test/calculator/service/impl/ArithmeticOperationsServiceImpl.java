package com.bndf1.test.calculator.service.impl;

import com.bndf1.test.calculator.domain.dto.OperandDTO;
import com.bndf1.test.calculator.domain.dto.ResultDTO;
import com.bndf1.test.calculator.exceptions.ApiExceptions;
import com.bndf1.test.calculator.exceptions.OperandException;
import com.bndf1.test.calculator.service.ArithmeticOperationsService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;

import static com.bndf1.test.calculator.exceptions.ApiExceptions.FIRST_OPERAND_IS_NULL;
import static com.bndf1.test.calculator.exceptions.ApiExceptions.SECOND_OPERAND_IS_NULL;

@Service
public class ArithmeticOperationsServiceImpl implements ArithmeticOperationsService {

  @Override
  public ResultDTO add(final OperandDTO dto) {
    final BigDecimal firstOperand = getOperand(dto.getFirstOperand(), FIRST_OPERAND_IS_NULL);
    final BigDecimal secondOperand = getOperand(dto.getSecondOperand(), SECOND_OPERAND_IS_NULL);

    return ResultDTO.builder().result(firstOperand.add(secondOperand)).build();
  }

  @Override
  public ResultDTO subtract(final OperandDTO dto) {
    final BigDecimal firstOperand = getOperand(dto.getFirstOperand(), FIRST_OPERAND_IS_NULL);
    final BigDecimal secondOperand = getOperand(dto.getSecondOperand(), SECOND_OPERAND_IS_NULL);

    return ResultDTO.builder().result(firstOperand.subtract(secondOperand)).build();
  }

  private BigDecimal getOperand(final BigDecimal operand, final ApiExceptions exception) {
    return Optional.ofNullable(operand).orElseThrow(() -> new OperandException(exception));
  }
}
