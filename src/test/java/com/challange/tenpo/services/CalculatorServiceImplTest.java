package com.challange.tenpo.services;

import com.challange.tenpo.dtos.ResultCalculatorDTO;
import com.challange.tenpo.exceptions.NotPorcentageApiExcenption;
import com.challange.tenpo.repositories.ExternalPorcentageRepository;
import com.challange.tenpo.services.impl.CalculatorServiceImpl;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.junit.jupiter.api.Assertions.assertThrows;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CalculatorServiceImplTest {

    private CalculatorService calculator;

    private ExternalPorcentageRepository expRepository;
    
    @BeforeEach
    public void setUp() {
    	expRepository = mock(ExternalPorcentageRepository.class);
    	calculator = new CalculatorServiceImpl(expRepository);
    }
    
    @Test
    public void GivenSum_WhenValuesAndPorcentageNotExist_ShouldReturnException() {
        // Arrange
        double a = 1.0;
        double b = 2.0;
        // Act
       
		assertThrows(NotPorcentageApiExcenption.class, () ->calculator.sum(a, b));
		
    }
    
    @Test
    public void GivenSum_WhenValuesAndPorcentageExist_ShouldReturnException() {
        // Arrange
        double a = 1.0;
        double b = 2.0;
        ResultCalculatorDTO expectedResult = new ResultCalculatorDTO(3.0);

        // Act
        calculator.updatePorcentage();
        ResultCalculatorDTO result;
		try {
			result = calculator.sum(a, b);
		} catch (NotPorcentageApiExcenption e) {
			result = new ResultCalculatorDTO(0.0);
		}

        // Assert
        assertEquals(result, expectedResult);
    }

}