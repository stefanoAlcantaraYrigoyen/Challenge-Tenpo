package com.challange.tenpo.repositories;

import java.util.List;

import com.challange.tenpo.entitys.History;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.actuate.trace.http.HttpTrace;
import org.springframework.boot.actuate.trace.http.HttpTraceRepository;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

@Component
@ConditionalOnProperty(prefix = "management.trace.http", name = "enabled", matchIfMissing = true)
@Slf4j
public class HttpTraceRepositoryImpl implements HttpTraceRepository {

    private HistoryRepository repository;

    @Override
    public void add(HttpTrace httpTrace) {
        History history = new History();
        history.setMethod(httpTrace.getRequest().getMethod());
        history.setEndpoint(httpTrace.getRequest().getUri().toString());
        history.setStatusCode(httpTrace.getResponse().getStatus());
        history.setTimeStamp(httpTrace.getTimestamp().toString());
        repository.save(history);
    }

    @Override
    public List<HttpTrace> findAll() {
        throw new UnsupportedOperationException("");
    }

	public HttpTraceRepositoryImpl(HistoryRepository repository) {
		super();
		this.repository = repository;
	}

}
