package com.bndf1.test.calculator.domain.dto;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder(toBuilder = true)
public class ResultDTO {
  BigDecimal result;
}
