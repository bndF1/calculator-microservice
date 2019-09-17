package com.bndf1.test.calculator.web.rest;

import com.bndf1.test.calculator.domain.dto.OperandDTO;
import com.bndf1.test.calculator.domain.dto.ResultDTO;
import com.bndf1.test.calculator.exceptions.ApiExceptions;
import com.bndf1.test.calculator.exceptions.OperandException;
import com.bndf1.test.calculator.service.ArithmeticOperationsService;
import org.jeasy.random.EasyRandom;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
class ArithmeticOperationsResourceTest {

  @Mock private ArithmeticOperationsService arithmeticOperationsService;

  @InjectMocks private ArithmeticOperationsResource arithmeticOperationsResource;

  @Test
  void addWithNullDtoTest() {
    final ResponseEntity<ResultDTO> result = this.arithmeticOperationsResource.add(null);

    final ArgumentCaptor<OperandDTO> operandDTOArgumentCaptor =
        ArgumentCaptor.forClass(OperandDTO.class);
    verify(this.arithmeticOperationsService, times(0)).add(operandDTOArgumentCaptor.capture());

    assertThat(result).isNotNull();
    assertThat(result).isEqualTo(ResponseEntity.badRequest().build());
  }

  @Test
  void addAndServiceThrowExceptionTest() {
    final EasyRandom easyRandom = new EasyRandom();
    final OperandException operandException =
        new OperandException(ApiExceptions.FIRST_OPERAND_IS_NULL);
    final OperandDTO operandDTO =
        OperandDTO.builder()
            .firstOperand(null)
            .secondOperand(BigDecimal.valueOf(easyRandom.nextDouble()))
            .build();

    when(this.arithmeticOperationsService.add(any(OperandDTO.class))).thenThrow(operandException);

    assertThrows(
        OperandException.class,
        () -> {
          final ArgumentCaptor<OperandDTO> operandDTOArgumentCaptor =
              ArgumentCaptor.forClass(OperandDTO.class);

          this.arithmeticOperationsResource.add(operandDTO);
          verify(this.arithmeticOperationsService, times(1))
              .add(operandDTOArgumentCaptor.capture());
        });
  }

  @Test
  void addWorksProperlyTest() {
    final EasyRandom easyRandom = new EasyRandom();

    final OperandDTO operandDTO = easyRandom.nextObject(OperandDTO.class);
    final ResultDTO resultDTO = easyRandom.nextObject(ResultDTO.class);
    when(this.arithmeticOperationsService.add(any(OperandDTO.class))).thenReturn(resultDTO);

    final ArgumentCaptor<OperandDTO> operandDTOArgumentCaptor =
        ArgumentCaptor.forClass(OperandDTO.class);

    final ResponseEntity<ResultDTO> result = this.arithmeticOperationsResource.add(operandDTO);
    verify(this.arithmeticOperationsService).add(operandDTOArgumentCaptor.capture());
    final OperandDTO captorValue = operandDTOArgumentCaptor.getValue();

    assertEquals(captorValue, operandDTO);
    assertThat(captorValue.getFirstOperand()).isEqualTo(operandDTO.getFirstOperand());
    assertThat(captorValue.getSecondOperand()).isEqualTo(operandDTO.getSecondOperand());

    assertThat(result.getBody()).isEqualTo(resultDTO);
  }

  @Test
  void subtractWithNullDtoTest() {
    final ResponseEntity<ResultDTO> result = this.arithmeticOperationsResource.subtract(null);
    final ArgumentCaptor<OperandDTO> operandDTOArgumentCaptor =
        ArgumentCaptor.forClass(OperandDTO.class);
    verify(this.arithmeticOperationsService, times(0)).subtract(operandDTOArgumentCaptor.capture());

    assertThat(result).isNotNull();
    assertThat(result).isEqualTo(ResponseEntity.badRequest().build());
  }

  @Test
  void subtractAndServiceThrowExceptionTest() {
    final EasyRandom easyRandom = new EasyRandom();
    final OperandException operandException =
        new OperandException(ApiExceptions.FIRST_OPERAND_IS_NULL);

    final OperandDTO operandDTO =
        OperandDTO.builder()
            .firstOperand(null)
            .secondOperand(BigDecimal.valueOf(easyRandom.nextDouble()))
            .build();

    when(this.arithmeticOperationsService.subtract(any(OperandDTO.class)))
        .thenThrow(operandException);

    assertThrows(
        OperandException.class,
        () -> {
          final ArgumentCaptor<OperandDTO> operandDTOArgumentCaptor =
              ArgumentCaptor.forClass(OperandDTO.class);

          this.arithmeticOperationsResource.subtract(operandDTO);
          verify(this.arithmeticOperationsService, times(1))
              .subtract(operandDTOArgumentCaptor.capture());
        });
  }

  @Test
  void subtractWorksProperlyTest() {
    final EasyRandom easyRandom = new EasyRandom();

    final OperandDTO operandDTO = easyRandom.nextObject(OperandDTO.class);
    final ResultDTO resultDTO = easyRandom.nextObject(ResultDTO.class);
    when(this.arithmeticOperationsService.subtract(any(OperandDTO.class))).thenReturn(resultDTO);

    final ArgumentCaptor<OperandDTO> operandDTOArgumentCaptor =
        ArgumentCaptor.forClass(OperandDTO.class);

    final ResponseEntity<ResultDTO> result = this.arithmeticOperationsResource.subtract(operandDTO);
    verify(this.arithmeticOperationsService).subtract(operandDTOArgumentCaptor.capture());
    final OperandDTO captorValue = operandDTOArgumentCaptor.getValue();

    assertEquals(captorValue, operandDTO);
    assertThat(captorValue.getFirstOperand()).isEqualTo(operandDTO.getFirstOperand());
    assertThat(captorValue.getSecondOperand()).isEqualTo(operandDTO.getSecondOperand());

    assertThat(result.getBody()).isEqualTo(resultDTO);
  }

  @Test
  void handleException() {
    final OperandException operandException =
        new OperandException(ApiExceptions.FIRST_OPERAND_IS_NULL);
    final ResponseEntity<String> exception =
        this.arithmeticOperationsResource.handleException(operandException);
    assertThat(exception).isNotNull();
    assertThat(exception.getBody()).isEqualTo(operandException.getLocalizedMessage());
    assertThat(exception.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
  }
}
