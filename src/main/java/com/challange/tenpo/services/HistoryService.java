package com.challange.tenpo.services;

import com.challange.tenpo.entitys.History;
import com.challange.tenpo.exceptions.NegativeParamException;

import java.util.List;

public interface HistoryService {

    List<History> getHistory(Integer pageNumber, Integer pageSize, String sortBy) throws NegativeParamException;

}
