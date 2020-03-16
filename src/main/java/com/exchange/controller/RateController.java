package com.exchange.controller;

import com.exchange.model.Rate;
import com.exchange.service.ExchangeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@RestController
@RequestMapping(value = "**/api/exchange-rates")
class RateController {


    @Autowired
    private ExchangeService service;

    @GetMapping(path = "/", produces = "application/json")
    public ResponseEntity<List<Rate>> getCommission() {
        return new ResponseEntity(service.getRate(), HttpStatus.OK);
    }

    @PostMapping(path = "/", consumes = "application/json", produces = "application/json")
    public ResponseEntity<Rate> postCommission(@RequestBody @Valid Rate rate) {
        service.postRate(rate);
        return new ResponseEntity(rate, HttpStatus.CREATED);
    }
}



