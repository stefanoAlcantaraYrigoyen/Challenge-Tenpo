package com.challange.tenpo.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.challange.tenpo.entitys.ExternalPorcentage;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(tags = "ExternalApi")
@RestController
@RequestMapping("/api")
public class ExternalApiController {

	@GetMapping("/porcentage")
    @ApiOperation(value = "Porcentage")
    public ResponseEntity<ExternalPorcentage> porcentage(@RequestHeader(required = true, value = "Authorization") String headerAuth) {
        return ResponseEntity.ok(new ExternalPorcentage(10D));
    }
}
