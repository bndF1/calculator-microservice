package com.bndf1.test.calculator.web.rest;

import com.bndf1.test.calculator.domain.dto.OperandDTO;
import com.bndf1.test.calculator.domain.dto.ResultDTO;
import com.bndf1.test.calculator.exceptions.ApiExceptions;
import com.bndf1.test.calculator.service.ArithmeticOperationsService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.jeasy.random.EasyRandom;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ExtendWith(SpringExtension.class)
public class ArithmeticOperationsResourceIT {

  @Autowired private MockMvc mockMvc;
  @Autowired private ArithmeticOperationsService arithmeticOperationsService;
  @Autowired private ObjectMapper objectMapper;

  @Test
  public void performAddOperationDeserializationTest() throws Exception {
    final EasyRandom easyRandom = new EasyRandom();
    final OperandDTO operandDTO = easyRandom.nextObject(OperandDTO.class);

    final MockHttpServletResponse response =
        mockMvc
            .perform(
                post("/api/add")
                    .contentType("application/json")
                    .content(objectMapper.writeValueAsString(operandDTO)))
            .andExpect(status().isOk())
            .andReturn()
            .getResponse();

    final ResultDTO resultDTO =
        objectMapper.readValue(response.getContentAsString(), ResultDTO.class);
    assertThat(resultDTO).isNotNull();
  }

  @Test
  public void performAddOperationTest() throws Exception {
    final EasyRandom easyRandom = new EasyRandom();
    final OperandDTO operandDTO = easyRandom.nextObject(OperandDTO.class);

    final MockHttpServletResponse response =
        mockMvc
            .perform(
                post("/api/add")
                    .contentType("application/json")
                    .content(objectMapper.writeValueAsString(operandDTO)))
            .andExpect(status().isOk())
            .andReturn()
            .getResponse();

    final ResultDTO resultDTO =
        objectMapper.readValue(response.getContentAsString(), ResultDTO.class);
    assertThat(resultDTO).isNotNull();
    assertThat(resultDTO.getResult())
        .isEqualTo(operandDTO.getFirstOperand().add(operandDTO.getSecondOperand()));
  }

  @Test
  public void performAddOperationTestWithBadRequest() throws Exception {

    final MockHttpServletResponse response =
        mockMvc
            .perform(
                post("/api/add")
                    .contentType("application/json")
                    .content(objectMapper.writeValueAsString(null)))
            .andExpect(status().isBadRequest())
            .andReturn()
            .getResponse();

    assertThat(response.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value());
  }

  @Test
  public void performAddOperationTestWithFirstOperandException() throws Exception {
    final EasyRandom easyRandom = new EasyRandom();
    final OperandDTO operandDTO =
        OperandDTO.builder()
            .firstOperand(null)
            .secondOperand(BigDecimal.valueOf(easyRandom.nextDouble()))
            .build();

    final MockHttpServletResponse response =
        mockMvc
            .perform(
                post("/api/add")
                    .contentType("application/json")
                    .content(objectMapper.writeValueAsString(operandDTO)))
            .andExpect(status().isBadRequest())
            .andReturn()
            .getResponse();

    assertThat(response).isNotNull();
    assertThat(response.getContentAsString())
        .isEqualTo(String.valueOf(ApiExceptions.FIRST_OPERAND_IS_NULL));
  }

  @Test
  public void performAddOperationTestWithSecondException() throws Exception {
    final EasyRandom easyRandom = new EasyRandom();
    final OperandDTO operandDTO =
        OperandDTO.builder()
            .firstOperand(BigDecimal.valueOf(easyRandom.nextDouble()))
            .secondOperand(null)
            .build();

    final MockHttpServletResponse response =
        mockMvc
            .perform(
                post("/api/add")
                    .contentType("application/json")
                    .content(objectMapper.writeValueAsString(operandDTO)))
            .andExpect(status().isBadRequest())
            .andReturn()
            .getResponse();

    assertThat(response).isNotNull();
    assertThat(response.getContentAsString())
        .isEqualTo(String.valueOf(ApiExceptions.SECOND_OPERAND_IS_NULL));
  }

  @Test
  public void performSubtractOperationDeserializationTest() throws Exception {
    final EasyRandom easyRandom = new EasyRandom();
    final OperandDTO operandDTO = easyRandom.nextObject(OperandDTO.class);

    final MockHttpServletResponse response =
        mockMvc
            .perform(
                post("/api/subtract")
                    .contentType("application/json")
                    .content(objectMapper.writeValueAsString(operandDTO)))
            .andExpect(status().isOk())
            .andReturn()
            .getResponse();

    final ResultDTO resultDTO =
        objectMapper.readValue(response.getContentAsString(), ResultDTO.class);
    assertThat(resultDTO).isNotNull();
    assertThat(resultDTO.getResult())
        .isEqualTo(operandDTO.getFirstOperand().subtract(operandDTO.getSecondOperand()));
  }

  @Test
  public void performSubtractOperationTestWithBadRequest() throws Exception {

    final MockHttpServletResponse response =
        mockMvc
            .perform(
                post("/api/subtract")
                    .contentType("application/json")
                    .content(objectMapper.writeValueAsString(null)))
            .andExpect(status().isBadRequest())
            .andReturn()
            .getResponse();

    assertThat(response.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value());
  }

  @Test
  public void performSubtractOperationTestWithFirstOperandException() throws Exception {
    final EasyRandom easyRandom = new EasyRandom();
    final OperandDTO operandDTO =
        OperandDTO.builder()
            .firstOperand(null)
            .secondOperand(BigDecimal.valueOf(easyRandom.nextDouble()))
            .build();

    final MockHttpServletResponse response =
        mockMvc
            .perform(
                post("/api/subtract")
                    .contentType("application/json")
                    .content(objectMapper.writeValueAsString(operandDTO)))
            .andExpect(status().isBadRequest())
            .andReturn()
            .getResponse();

    assertThat(response).isNotNull();
    assertThat(response.getContentAsString())
        .isEqualTo(String.valueOf(ApiExceptions.FIRST_OPERAND_IS_NULL));
  }
}
