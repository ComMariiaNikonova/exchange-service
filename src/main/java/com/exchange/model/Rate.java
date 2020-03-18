package com.exchange.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;

@Data
@Table(name = "rate")
@IdClass(CommissionId.class)
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Rate implements Serializable {

    @DecimalMin(value = "0.00", message = "Rate should be grater than 0.00 ")
    @Column(name = "rate")
    private BigDecimal rate;

    @Id
    @Column(name = "currency_from")
    @Enumerated(EnumType.STRING)
    @NotNull
    private Currency from;

    @Id
    @Column(name = "currency_to")
    @Enumerated(EnumType.STRING)
    @NotNull
    private Currency to;

    public Rate roundDecimal() {
        setRate(rate.setScale(2, BigDecimal.ROUND_HALF_UP));
        return this;
    }
}

