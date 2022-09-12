package com.challange.tenpo.services;

import com.challange.tenpo.dtos.ResultCalculatorDTO;
import com.challange.tenpo.exceptions.NotPorcentageApiExcenption;

public interface CalculatorService {

    ResultCalculatorDTO sum(double num1, double num2) throws NotPorcentageApiExcenption;
    void updatePorcentage();
}
