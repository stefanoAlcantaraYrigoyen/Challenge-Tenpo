package com.challange.tenpo.services;

import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;

import com.challange.tenpo.entitys.History;
import com.challange.tenpo.exceptions.NegativeParamException;
import com.challange.tenpo.repositories.HistoryRepository;
import com.challange.tenpo.services.impl.HistoryServiceImpl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public class HistoryServiceImplTest {

    private HistoryService service;
    private HistoryRepository repository;

    @BeforeEach
    public void setUp() {
        repository = mock(HistoryRepository.class);
        service = new HistoryServiceImpl(repository);
    }

    @Test
    public void GivenGetHistory_WhenHasHistory_ShouldReturnHistory() throws NegativeParamException {
        // Arrange
        History trace = new History(1L, "/test", 200, "11", "GET");

        Page<History> traces = mock(Page.class);
        when(repository.findAll(any(Pageable.class))).thenReturn(traces);
        when(traces.hasContent()).thenReturn(true);
        when(traces.getContent()).thenReturn(List.of(trace));

        // Act
        List<History> results = service.getHistory(0, 10, "id");

        // Assert
        assertEquals(results.size(), 1);
        assertEquals(results.get(0), trace);

    }

    @Test
    public void GivenGetHistory_WhenHasNotHistory_ShouldReturnResultEmpty() throws NegativeParamException {
        // Arrange
        Page<History> traces = mock(Page.class);
        when(repository.findAll(any(Pageable.class))).thenReturn(traces);
        when(traces.hasContent()).thenReturn(false);
        when(traces.getContent()).thenReturn(List.of());

        // Act
        List<History> results = service.getHistory(0, 10, "id");

        // Assert
        assertEquals(results.size(), 0);
    }

}
