package com.challange.tenpo.controllers;

import com.challange.tenpo.entitys.History;
import com.challange.tenpo.exceptions.NegativeParamException;
import com.challange.tenpo.services.HistoryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Validated
@RestController
@RequestMapping("/tenpo")
@Api(tags = "History")
public class HistoryController {

    private final HistoryService historyService;

    public HistoryController(HistoryService historyService) {
		super();
		this.historyService = historyService;
	}
    
	@GetMapping(value = "/history")
    @ApiOperation(value = "Get History")
    public ResponseEntity<List<History>> getHistoryHistory(
            @RequestHeader(required = true, value = "Authorization") String auth,
            @RequestParam(defaultValue = "0") Integer pageNumber,
            @RequestParam(defaultValue = "10") Integer pageSize,
            @RequestParam(defaultValue = "id") String sortBy) throws NegativeParamException {
        return ResponseEntity.ok(historyService.getHistory(pageNumber, pageSize, sortBy));
    }

}

