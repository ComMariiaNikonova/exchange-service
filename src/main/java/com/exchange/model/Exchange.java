package com.exchange.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Exchange implements Serializable {

    OperationType operationType;
    @DecimalMin(value = "0.00", message = "Amount be grater than 0.00 ")
    private BigDecimal amountFrom;
    @DecimalMin(value = "0.00", message = "Amount be grater than 0.00 ")
    private BigDecimal amountTo;
    @Enumerated(EnumType.STRING)
    @NotNull
    private Currency currencyFrom;
    @Enumerated(EnumType.STRING)
    @NotNull
    private Currency currencyTo;

    public Exchange roundDecimal() {
        setAmountTo(amountTo.setScale(2, BigDecimal.ROUND_HALF_UP));
        setAmountFrom(amountFrom.setScale(2, BigDecimal.ROUND_HALF_UP));
        return this;
    }
}
