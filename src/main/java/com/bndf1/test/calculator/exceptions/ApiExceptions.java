package com.bndf1.test.calculator.exceptions;

public enum ApiExceptions {
  FIRST_OPERAND_IS_NULL("The first operand is null"),
  SECOND_OPERAND_IS_NULL("The second operand is null"),
  NAN_OR_INFINITE("The result is NaN or Infinity");

  ApiExceptions(final String exception) {}
}
