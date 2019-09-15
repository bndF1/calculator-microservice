package com.bndf1.test.calculator.service.impl;

import com.bndf1.test.calculator.domain.dto.OperandDTO;
import com.bndf1.test.calculator.domain.dto.ResultDTO;
import com.bndf1.test.calculator.service.ArithmeticOperationsService;

public class ArithmeticOperationsServiceImpl implements ArithmeticOperationsService {
    @Override
    public ResultDTO add(OperandDTO dto) {
        return ResultDTO.builder().build();
    }
}
