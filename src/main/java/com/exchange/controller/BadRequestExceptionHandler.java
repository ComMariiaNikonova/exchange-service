package com.exchange.controller;

import com.exchange.model.ClientError;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@ControllerAdvice
@RestController
public class BadRequestExceptionHandler {

    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity handleConflict(IllegalArgumentException e) {

        return new ResponseEntity(new ClientError(e.getCause().getMessage()),
                HttpStatus.BAD_REQUEST);
    }
}
