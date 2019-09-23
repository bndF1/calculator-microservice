package com.bndf1.test.calculator.domain.dto;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
@Builder(toBuilder = true)
public class ResultDTO implements Serializable {
  private static final long serialVersionUID = -6543262445504289504L;
  BigDecimal result;
}
