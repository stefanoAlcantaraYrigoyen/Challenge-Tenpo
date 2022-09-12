package com.challange.tenpo.controllers;

import com.challange.tenpo.dtos.ResultCalculatorDTO;
import com.challange.tenpo.exceptions.NotPorcentageApiExcenption;
import com.challange.tenpo.services.CalculatorService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import javax.validation.constraints.NotEmpty;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "Calculator")
@RestController
@RequestMapping("/tenpo/calculator")
public class CalculatorController {

	@Autowired
    private CalculatorService calculatorService;

	@GetMapping("/sum")
    @ApiOperation(value = "Suma")
    public ResponseEntity<ResultCalculatorDTO> addition(
            @RequestHeader(required = true, value = "Authorization") String headerAuth,
            @RequestParam(required = true) @NotEmpty double num1,
            @RequestParam(required = true) @NotEmpty double num2) throws NotPorcentageApiExcenption{
        return ResponseEntity.ok(calculatorService.sum(num1, num2));
    }
	
	@Scheduled(cron = "0 0/30 * * * ?")
    public void updateAdditionPorcentage() throws NotPorcentageApiExcenption{
		calculatorService.updatePorcentage();
    }
	
	@GetMapping("/procentage")
    @ApiOperation(value = "Porcentage")
    public ResponseEntity<Double> porcentage() {
        return ResponseEntity.ok(10D);
    }
}