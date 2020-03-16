package com.exchange.controller;

import com.exchange.model.Commission;
import com.exchange.service.ExchangeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@RestController
@RequestMapping(value = "**/api/commission")
class CommissionController {


    @Autowired
    private ExchangeService service;

    @GetMapping(path = "/", produces = "application/json")
    public ResponseEntity<List<Commission>> getCommission() {
        return new ResponseEntity(service.getCommissionPt(), HttpStatus.OK);
    }

    @PostMapping(path = "/", consumes = "application/json", produces = "application/json")
    public ResponseEntity<Commission> postCommission(@RequestBody @Valid Commission commission) {
        service.postCommissionPt(commission);
        return new ResponseEntity(commission, HttpStatus.CREATED);
    }
}



