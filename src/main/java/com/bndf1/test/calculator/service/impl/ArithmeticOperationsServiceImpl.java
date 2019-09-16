package com.bndf1.test.calculator.service.impl;

import com.bndf1.test.calculator.domain.dto.OperandDTO;
import com.bndf1.test.calculator.domain.dto.ResultDTO;
import com.bndf1.test.calculator.exceptions.OperandException;
import com.bndf1.test.calculator.service.ArithmeticOperationsService;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static com.bndf1.test.calculator.exceptions.ApiExceptions.*;

@Service
public class ArithmeticOperationsServiceImpl implements ArithmeticOperationsService {

  @Override
  public ResultDTO add(final OperandDTO dto) {
    final Double firstOperand =
        Optional.ofNullable(dto.getFirstOperand())
            .orElseThrow(() -> new OperandException(FIRST_OPERAND_IS_NULL));

    final Double secondOperand =
        Optional.ofNullable(dto.getSecondOperand())
            .orElseThrow(() -> new OperandException(SECOND_OPERAND_IS_NULL));

    final Double result =
        Optional.of(Double.sum(firstOperand, secondOperand))
            .filter(resultDouble -> !resultDouble.isNaN() && !resultDouble.isInfinite())
            .orElseThrow(() -> new OperandException(NAN_OR_INFINITE));

    return ResultDTO.builder().result(result).build();
  }

  @Override
  public ResultDTO subtract(final OperandDTO dto) {
    final Double firstOperand =
        Optional.ofNullable(dto.getFirstOperand())
            .orElseThrow(() -> new OperandException(FIRST_OPERAND_IS_NULL));

    final Double secondOperand =
        Optional.ofNullable(dto.getSecondOperand())
            .orElseThrow(() -> new OperandException(SECOND_OPERAND_IS_NULL));

    return ResultDTO.builder().build();
  }
}
