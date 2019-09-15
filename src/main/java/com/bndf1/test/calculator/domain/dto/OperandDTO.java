package com.bndf1.test.calculator.domain.dto;

import lombok.Builder;
import lombok.Value;

@Value
@Builder(toBuilder = true)
public class OperandDTO {
  Double firstOperand;
  Double secondOperand;
}
