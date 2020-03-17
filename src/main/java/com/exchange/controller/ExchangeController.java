package com.exchange.controller;

import com.exchange.model.Exchange;
import com.exchange.service.ExchangeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@RestController
@RequestMapping(value = "**/api/exchange")
@RequiredArgsConstructor
class ExchangeController {

    private final ExchangeService service;

    @ResponseStatus(value = HttpStatus.CREATED)
    @PostMapping(path = "/")
    public Exchange postCommission(@RequestBody @Valid Exchange exchange) {
        return service.handleExchange(exchange);
    }
}



