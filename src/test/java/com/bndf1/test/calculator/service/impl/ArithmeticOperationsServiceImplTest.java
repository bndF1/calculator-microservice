package com.bndf1.test.calculator.service.impl;

import com.bndf1.test.calculator.domain.dto.OperandDTO;
import com.bndf1.test.calculator.domain.dto.ResultDTO;
import com.bndf1.test.calculator.exceptions.ApiExceptions;
import com.bndf1.test.calculator.exceptions.OperandException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(SpringExtension.class)
class ArithmeticOperationsServiceImplTest {

  @InjectMocks private ArithmeticOperationsServiceImpl arithmeticOperationsServiceImpl;

  @Test
  void addWitAllOperandsNullThrowsExceptionTest() {
    final OperandDTO operandDTO = OperandDTO.builder().build();
    final OperandException operandException =
        assertThrows(
            OperandException.class, () -> this.arithmeticOperationsServiceImpl.add(operandDTO));
    assertThat(operandException.getLocalizedMessage())
        .isEqualTo(String.valueOf(ApiExceptions.FIRST_OPERAND_IS_NULL));
  }

  @Test
  void addWithSecondOperandNullThrowsExceptionTest() {
    final OperandDTO operandDTO =
        OperandDTO.builder().firstOperand(Double.MAX_VALUE).secondOperand(null).build();
    final OperandException operandException =
        assertThrows(
            OperandException.class, () -> this.arithmeticOperationsServiceImpl.add(operandDTO));
    assertThat(operandException.getLocalizedMessage())
        .isEqualTo(String.valueOf(ApiExceptions.SECOND_OPERAND_IS_NULL));
  }

  @Test
  void addWithNaNOperandThrowsExceptionTest() {
    final OperandDTO operandDTO =
        OperandDTO.builder().firstOperand(Double.NaN).secondOperand(Double.MAX_VALUE).build();
    final OperandException operandException =
        assertThrows(
            OperandException.class, () -> this.arithmeticOperationsServiceImpl.add(operandDTO));
    assertThat(operandException.getLocalizedMessage())
        .isEqualTo(String.valueOf(ApiExceptions.NAN_OR_INFINITE));
  }

  @Test
  void addWithInfinityOperandThrowsExceptionTest() {
    final OperandDTO operandDTO =
        OperandDTO.builder()
            .firstOperand(Double.MIN_NORMAL)
            .secondOperand(Double.NEGATIVE_INFINITY)
            .build();
    final OperandException operandException =
        assertThrows(
            OperandException.class, () -> this.arithmeticOperationsServiceImpl.add(operandDTO));
    assertThat(operandException.getLocalizedMessage())
        .isEqualTo(String.valueOf(ApiExceptions.NAN_OR_INFINITE));
  }

  @Test
  void addWithOperandDTOValuesShouldWorkProperly() {
    final double resultExpected = Double.sum(Double.MIN_NORMAL, Double.MAX_VALUE);

    final OperandDTO operandDTO =
        OperandDTO.builder()
            .firstOperand(Double.MIN_NORMAL)
            .secondOperand(Double.MAX_VALUE)
            .build();

    final ResultDTO resultDto = this.arithmeticOperationsServiceImpl.add(operandDTO);

    assertThat(resultDto.getResult()).isEqualTo(resultExpected);
  }
}
