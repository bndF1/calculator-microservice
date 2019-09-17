package com.bndf1.test.calculator.domain.dto;

import lombok.Builder;
import lombok.Value;

import java.math.BigDecimal;

@Value
@Builder(toBuilder = true)
public class OperandDTO {
  BigDecimal firstOperand;
  BigDecimal secondOperand;
}
