package com.exchange.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;

@Data
@Table(name = "commission")
@IdClass(CommissionId.class)
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Commission implements Serializable {

    @DecimalMin(value = "0.00", message = "Commission percent should be grater than 0.00 " +
            "Commission percent should be in range 0.00 - 100.00")
    @DecimalMax(value = "100.00", message = "Commission percent should be lower than 100.00 " +
            "Commission percent should be in range 0.00 - 100.00")
    @Column(name = "commission")
    private BigDecimal commissionPt;

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

    public Commission roundDecimal() {
        setCommissionPt(commissionPt.setScale(2, BigDecimal.ROUND_HALF_UP));
        return this;
    }
}

