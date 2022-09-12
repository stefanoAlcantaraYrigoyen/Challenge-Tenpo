package com.challange.tenpo.repositories;

import com.challange.tenpo.entitys.History;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

public interface HistoryRepository extends PagingAndSortingRepository<History, Long> {
}