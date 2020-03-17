package com.exchange.controller;

import com.exchange.model.Commission;
import com.exchange.service.ExchangeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@RestController
@RequestMapping(value = "**/api/commissions")
@RequiredArgsConstructor
class CommissionController {

    private final ExchangeService service;

    @GetMapping(path = "/")
    public List<Commission> getCommission() {
        return service.getCommissionPt();
    }

    @ResponseStatus(value = HttpStatus.CREATED)
    @PostMapping(path = "/")
    public Commission postCommission(@RequestBody @Valid Commission commission) {
        return service.createCommissionPt(commission);
    }
}



