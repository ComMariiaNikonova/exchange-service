package com.exchange.model;

import lombok.Data;

import java.io.Serializable;


@Data
public class CommissionId implements Serializable {

    Currency from;
    Currency to;
}
