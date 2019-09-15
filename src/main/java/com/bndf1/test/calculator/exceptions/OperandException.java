package com.bndf1.test.calculator.exceptions;

public class OperandException extends RuntimeException {
  private static final long serialVersionUID = -4401436013663869885L;

  public OperandException(final ApiExceptions message) {
    super(String.valueOf(message));
  }
}
