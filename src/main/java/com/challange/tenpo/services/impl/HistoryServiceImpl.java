package com.challange.tenpo.services.impl;

import com.challange.tenpo.entitys.History;

import java.util.List;

import com.challange.tenpo.exceptions.NegativeParamException;
import com.challange.tenpo.repositories.HistoryRepository;
import com.challange.tenpo.services.HistoryService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import static com.challange.tenpo.config.Consts.NEGATIVE_PARAM_EXCEPTION;

@Service
public class HistoryServiceImpl implements HistoryService {

    private HistoryRepository repository;

    @Override
    public List<History> getHistory(Integer pageNumber, Integer pageSize, String sortBy) throws NegativeParamException {
        if (pageSize < 0) {
            throw new NegativeParamException(NEGATIVE_PARAM_EXCEPTION.concat(": pageSize"));
        } else if (pageNumber < 0) {
            throw new NegativeParamException(NEGATIVE_PARAM_EXCEPTION.concat(": pageNumber"));
        }
        Pageable paging = PageRequest.of(pageNumber, pageSize, Sort.by(sortBy));
        Page<History> pagedResult = repository.findAll(paging);
        return pagedResult.hasContent() ? pagedResult.getContent() : List.of();
    }

	public HistoryServiceImpl(HistoryRepository repository) {
		super();
		this.repository = repository;
	}
    
}
