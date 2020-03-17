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
@RequiredArgsConstructor
class RateController {

    private final ExchangeService service;

    @GetMapping(path = "/")
    public List<Rate> getCommission() {
        return service.getRate();
    }

    @ResponseStatus(value = HttpStatus.CREATED)
    @PostMapping(path = "/")
    public Rate postCommission(@RequestBody @Valid Rate rate) {
        return service.createRate(rate);
    }
}



