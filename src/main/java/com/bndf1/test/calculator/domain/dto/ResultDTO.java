package com.bndf1.test.calculator.domain.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder(toBuilder = true)
public class ResultDTO {
  @Builder.Default double result = 0d;
}
