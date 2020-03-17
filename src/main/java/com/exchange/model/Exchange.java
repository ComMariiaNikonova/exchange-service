package com.exchange.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.NumberFormat;

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

    @DecimalMin(value = "0.00", message = "Amount be grater than 0.00 ")
    @NumberFormat(pattern = "#0,00")
    BigDecimal amountTo;

    @DecimalMin(value = "0.00", message = "Amount be grater than 0.00 ")
    @NumberFormat(pattern = "#0,00")
    BigDecimal amountFrom;

    @Enumerated(EnumType.STRING)
    @NotNull
    Currency currencyFrom;

    @Enumerated(EnumType.STRING)
    @NotNull
    Currency currencyTo;

    OperationType operationType;

}
