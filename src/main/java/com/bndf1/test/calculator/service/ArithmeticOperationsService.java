package com.bndf1.test.calculator.service;

import com.bndf1.test.calculator.domain.dto.OperandDTO;
import com.bndf1.test.calculator.domain.dto.ResultDTO;

public interface ArithmeticOperationsService {
  ResultDTO add(OperandDTO dto);

  ResultDTO subtract(OperandDTO dto);
}
