package com.exchange.controller;

import com.exchange.model.Rate;
import com.exchange.service.ExchangeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@RestController
@RequestMapping(value = "**/api/exchange-rates")
@ControllerAdvice
@RequiredArgsConstructor
class RateController {

    private final ExchangeService service;

    @GetMapping(path = "/", produces = "application/json")
    public List<Rate> getCommission() {
        return service.getRate();
    }

    @ResponseStatus(value = HttpStatus.CREATED)
    @PostMapping(path = "/", consumes = "application/json", produces = "application/json")
    public Rate postCommission(@RequestBody @Valid Rate rate) {
        return service.createRate(rate);
    }
}



