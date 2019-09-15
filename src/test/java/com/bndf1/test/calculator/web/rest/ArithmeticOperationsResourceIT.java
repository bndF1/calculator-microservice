package com.bndf1.test.calculator.web.rest;

import com.bndf1.test.calculator.domain.dto.OperandDTO;
import com.bndf1.test.calculator.domain.dto.ResultDTO;
import com.bndf1.test.calculator.service.ArithmeticOperationsService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.jeasy.random.EasyRandom;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ExtendWith(SpringExtension.class)
public class ArithmeticOperationsResourceIT {

  @Autowired MockMvc mockMvc;
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
        .isEqualTo(Double.sum(operandDTO.getFirstOperand(), operandDTO.getSecondOperand()));
  }
}
