package com.exchange.model;

import com.exchange.util.deserializer.CurrencyDeserializer;
import com.exchange.util.deserializer.OperationDeserializer;
import com.exchange.util.serializer.BigDecimalSerializer;
import com.exchange.util.serializer.CurrencySerializer;
import com.exchange.util.serializer.OperationSerializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.deser.std.NumberDeserializers;
import lombok.Data;
import org.springframework.format.annotation.NumberFormat;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;

@Data
public class Exchange implements Serializable {

    @DecimalMin(value = "0.00", message = "Amount be grater than 0.00 ")
    @NumberFormat(pattern = "#0,00")
    @JsonDeserialize(using = NumberDeserializers.BigDecimalDeserializer.class)
    @JsonSerialize(using = BigDecimalSerializer.class)
    BigDecimal amountTo;

    @DecimalMin(value = "0.00", message = "Amount be grater than 0.00 ")
    @NumberFormat(pattern = "#0,00")
    @JsonDeserialize(using = NumberDeserializers.BigDecimalDeserializer.class)
    @JsonSerialize(using = BigDecimalSerializer.class)
    BigDecimal amountFrom;

    @JsonDeserialize(using = CurrencyDeserializer.class)
    @JsonSerialize(using = CurrencySerializer.class)
    @Enumerated(EnumType.STRING)
    @NotNull
    Currency currencyFrom;

    @JsonSerialize(using = CurrencySerializer.class)
    @JsonDeserialize(using = CurrencyDeserializer.class)
    @Enumerated(EnumType.STRING)
    @NotNull
    Currency currencyTo;

    @JsonSerialize(using = OperationSerializer.class)
    @JsonDeserialize(using = OperationDeserializer.class)
    OperationType operationType;

}
