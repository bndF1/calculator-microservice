package com.bndf1.test.calculator.web.rest;

import com.bndf1.test.calculator.service.ArithmeticOperationsService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
@AutoConfigureMockMvc
@ExtendWith(SpringExtension.class)
public class ArithmeticOperationsResourceIT {

  @Autowired MockMvc mockMvc;

  @MockBean
  private ArithmeticOperationsService arithmeticOperationsService;

  @Test
  public void performAddOperationTest() {

      when(arithmeticOperationsService.add(any())).thenReturn(2d);

  }
}
