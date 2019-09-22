package com.bndf1.test.calculator.domain.dto;

import lombok.Builder;
import lombok.Value;

import java.io.Serializable;
import java.math.BigDecimal;

@Value
@Builder(toBuilder = true)
public class OperandDTO implements Serializable {
  private static final long serialVersionUID = 3378723997062490515L;
  BigDecimal firstOperand;
  BigDecimal secondOperand;
}
