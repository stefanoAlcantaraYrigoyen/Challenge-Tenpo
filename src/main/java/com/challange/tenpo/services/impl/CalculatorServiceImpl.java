package com.challange.tenpo.services.impl;

import com.challange.tenpo.dtos.ResultCalculatorDTO;
import com.challange.tenpo.entitys.ExternalPorcentage;
import com.challange.tenpo.exceptions.NotPorcentageApiExcenption;
import com.challange.tenpo.repositories.ExternalPorcentageRepository;
import com.challange.tenpo.services.CalculatorService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import static com.challange.tenpo.config.Consts.API_PORCENTAGE_NOT_FOUND;

@Service
public class CalculatorServiceImpl implements CalculatorService{

	private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(CalculatorService.class);
	
	private ExternalPorcentageRepository expRepository;
	
	@Autowired
	private RestTemplate restTemplate;
	
    public CalculatorServiceImpl(ExternalPorcentageRepository exp) {
    	super();
    	expRepository = exp;
	}

	@Override
    public ResultCalculatorDTO sum(double num1, double num2) throws NotPorcentageApiExcenption{
    	ExternalPorcentage porcentage = expRepository.findTopByOrderByIdDesc();
    	if(porcentage == null) {
    		throw new NotPorcentageApiExcenption(API_PORCENTAGE_NOT_FOUND);
    	}
        log.info("[Log] Processing Sum: {} + {} + {} porcentege of the result", num1, num2, porcentage);
        Double porcent = (num1 + num2) * (porcentage.getPorcentage() / 100);
        Double result = num1 + num2;
        return new ResultCalculatorDTO(result + porcent);
    }

	@Override
	public void updatePorcentage() {
//		String url = "http://localhost:8080/api/porcentage";
//		ResponseEntity<ExternalPorcentage> result = restTemplate.getForEntity(url, ExternalPorcentage.class);
//		if(result == null || result.getBody() == null) {
//			ExternalPorcentage porcentage = new ExternalPorcentage();
//			porcentage.setPorcentage(10D);
//			expRepository.save(porcentage);
//		}else {
//			expRepository.save(result.getBody());
//		}
		forceUpdatePorcentage();
	}

	@Override
	public ExternalPorcentage forceUpdatePorcentage() {
		ExternalPorcentage porcentage = new ExternalPorcentage();
		porcentage.setPorcentage(10D);
		return expRepository.save(porcentage);
	}
}
