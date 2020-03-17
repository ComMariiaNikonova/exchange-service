package com.exchange.controller;

import com.exchange.model.Commission;
import com.exchange.service.ExchangeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@RestController
@RequestMapping(value = "**/api/commission")
@ControllerAdvice
@RequiredArgsConstructor
class CommissionController {

    private final ExchangeService service;

    @GetMapping(path = "/", produces = "application/json")
    public List<Commission> getCommission() {
        return service.getCommissionPt();
    }

    @ResponseStatus(value = HttpStatus.CREATED)
    @PostMapping(path = "/", consumes = "application/json", produces = "application/json")
    public Commission postCommission(@RequestBody @Valid Commission commission) {
        return service.createCommissionPt(commission);
    }
}



