package com.challange.tenpo.services.impl;

import com.challange.tenpo.dtos.ResultCalculatorDTO;
import com.challange.tenpo.entitys.ExternalPorcentage;
import com.challange.tenpo.exceptions.NotPorcentageApiExcenption;
import com.challange.tenpo.repositories.ExternalPorcentageRepository;
import com.challange.tenpo.services.CalculatorService;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import static com.challange.tenpo.config.Consts.API_PORCENTAGE_NOT_FOUND;
import org.springframework.web.client.RestTemplate;

@Service
public class CalculatorServiceImpl implements CalculatorService{

	private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(CalculatorService.class);
	
	private ExternalPorcentageRepository expRepository;
	
    public CalculatorServiceImpl(ExternalPorcentageRepository exp) {
    	expRepository = exp;
	}

	@Override
    public ResultCalculatorDTO sum(double num1, double num2) throws NotPorcentageApiExcenption{
    	ExternalPorcentage porcentage = expRepository.findTopByOrderByIdDesc();
    	if(porcentage == null) {
    		throw new NotPorcentageApiExcenption(API_PORCENTAGE_NOT_FOUND);
    	}
        log.info("[Log] Processing Sum: {} + {} + {} porcentege of the result", num1, num2, porcentage);
        Double result = (num1 + num2) * (porcentage.getPorcentage() / 100);
        return new ResultCalculatorDTO(result);
    }

	@Override
	public void updatePorcentage() {
		String uri = "http://localhost:8080/tenpo/calculator/porcentage";
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<Double> value = restTemplate.getForEntity(uri, Double.class);
		ExternalPorcentage porcentage = new ExternalPorcentage(value.getBody());
		expRepository.save(porcentage);
	}

}
