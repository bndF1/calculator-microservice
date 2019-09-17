package com.bndf1.test.calculator.service.impl;

import com.bndf1.test.calculator.domain.dto.OperandDTO;
import com.bndf1.test.calculator.domain.dto.ResultDTO;
import com.bndf1.test.calculator.exceptions.ApiExceptions;
import com.bndf1.test.calculator.exceptions.OperandException;
import io.corp.calculator.TracerImpl;
import org.jeasy.random.EasyRandom;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
class ArithmeticOperationsServiceImplTest {

  @Mock private TracerImpl tracer;

  @InjectMocks private ArithmeticOperationsServiceImpl arithmeticOperationsServiceImpl;

  @Before
  public void setUp() {
    doNothing().when(this.tracer).trace(any());
  }

  @Test
  void addWithAllOperandsNullThrowsExceptionTest() {
    final OperandDTO operandDTO = OperandDTO.builder().build();
    final OperandException operandException =
        assertThrows(
            OperandException.class, () -> this.arithmeticOperationsServiceImpl.add(operandDTO));

    verifyZeroInteractions(this.tracer);
    assertThat(operandException.getLocalizedMessage())
        .isEqualTo(String.valueOf(ApiExceptions.FIRST_OPERAND_IS_NULL));
  }

  @Test
  void addWithSecondOperandNullThrowsExceptionTest() {
    final OperandDTO operandDTO =
        OperandDTO.builder()
            .firstOperand(BigDecimal.valueOf(Double.MAX_VALUE))
            .secondOperand(null)
            .build();

    final OperandException operandException =
        assertThrows(
            OperandException.class, () -> this.arithmeticOperationsServiceImpl.add(operandDTO));

    verifyZeroInteractions(this.tracer);
    assertThat(operandException.getLocalizedMessage())
        .isEqualTo(String.valueOf(ApiExceptions.SECOND_OPERAND_IS_NULL));
  }

  @Test
  void addWithOperandDTOValuesShouldWorkProperly() {
    final BigDecimal resultExpected =
        BigDecimal.valueOf(Double.MIN_NORMAL).add(BigDecimal.valueOf(Double.MAX_VALUE));

    final OperandDTO operandDTO =
        OperandDTO.builder()
            .firstOperand(BigDecimal.valueOf(Double.MIN_NORMAL))
            .secondOperand(BigDecimal.valueOf(Double.MAX_VALUE))
            .build();

    final ArgumentCaptor<BigDecimal> tracerArgumentCaptor =
        ArgumentCaptor.forClass(BigDecimal.class);

    final ResultDTO resultDto = this.arithmeticOperationsServiceImpl.add(operandDTO);

    verify(this.tracer, times(1)).trace(tracerArgumentCaptor.capture());
    assertThat(resultDto.getResult()).isEqualTo(resultExpected);
  }

  @Test
  void subtractOperationWithNullOperandsTest() {
    final OperandDTO operandDTO = OperandDTO.builder().build();

    final OperandException operandException =
        assertThrows(
            OperandException.class,
            () -> this.arithmeticOperationsServiceImpl.subtract(operandDTO));

    verifyZeroInteractions(this.tracer);
    assertThat(operandException.getLocalizedMessage())
        .isEqualTo(String.valueOf(ApiExceptions.FIRST_OPERAND_IS_NULL));
  }

  @Test
  void subtractOperationWithSecondOperandNullTest() {
    final OperandDTO operandDTO =
        OperandDTO.builder()
            .firstOperand(BigDecimal.valueOf(Double.MAX_VALUE))
            .secondOperand(null)
            .build();

    final OperandException operandException =
        assertThrows(
            OperandException.class,
            () -> this.arithmeticOperationsServiceImpl.subtract(operandDTO));

    verifyZeroInteractions(this.tracer);
    assertThat(operandException.getLocalizedMessage())
        .isEqualTo(String.valueOf(ApiExceptions.SECOND_OPERAND_IS_NULL));
  }

  @Test
  void subtractOperationShouldWorkTest() {
    final EasyRandom easyRandom = new EasyRandom();
    final BigDecimal firstOperand = BigDecimal.valueOf(easyRandom.nextGaussian());
    final BigDecimal secondOperand = BigDecimal.valueOf(easyRandom.nextDouble());

    final BigDecimal resultExpected = firstOperand.subtract(secondOperand);

    final OperandDTO operandDTO =
        OperandDTO.builder().firstOperand(firstOperand).secondOperand(secondOperand).build();

    final ArgumentCaptor<BigDecimal> tracerArgumentCaptor =
        ArgumentCaptor.forClass(BigDecimal.class);

    final ResultDTO resultDTO = this.arithmeticOperationsServiceImpl.subtract(operandDTO);

    verify(this.tracer, times(1)).trace(tracerArgumentCaptor.capture());
    assertThat(resultDTO.getResult()).isEqualTo(resultExpected);
  }
}
