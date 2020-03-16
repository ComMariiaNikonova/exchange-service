package com.exchange.model;

import lombok.Data;

import java.io.Serializable;


@Data
public class RateId implements Serializable {

    Currency from;
    Currency to;
}
