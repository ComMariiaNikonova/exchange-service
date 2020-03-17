package com.exchange.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.NumberFormat;

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
    @NumberFormat(pattern = "#0.00")
    BigDecimal rate;

    @Id
    @Column(name = "currency_from")
    @Enumerated(EnumType.STRING)
    @NotNull
    Currency from;

    @Id
    @Column(name = "currency_to")
    @Enumerated(EnumType.STRING)
    @NotNull
    Currency to;
}

