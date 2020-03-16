package com.exchange.controller;

import com.exchange.model.Exchange;
import com.exchange.service.ExchangeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;


@RestController
@RequestMapping(value = "**/api/exchange")
class ExchangeController {

    @Autowired
    private ExchangeService service;

    @ExceptionHandler(IllegalArgumentException.class)
    @PostMapping(path = "/", consumes = "application/json", produces = "application/json")
    public ResponseEntity<Exchange> postCommission(@RequestBody @Valid Exchange exchange) {
        try {
            return new ResponseEntity(service.handleExchange(exchange), HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "Invalid Request contract:" + e.getMessage(), e);
        }
    }
}



