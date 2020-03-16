package com.exchange.model;

import com.exchange.util.deserializer.CurrencyDeserializer;
import com.exchange.util.serializer.BigDecimalSerializer;
import com.exchange.util.serializer.CurrencySerializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.deser.std.NumberDeserializers;
import lombok.Data;
import org.springframework.format.annotation.NumberFormat;

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
public class Commission implements Serializable {

    @DecimalMin(value = "0.00", message = "Commission percent should be grater than 0.00 " +
            "Commission percent should be in range 0.00 - 100.00")
    @DecimalMax(value = "100.00", message = "Commission percent should be lower than 100.00 " +
            "Commission percent should be in range 0.00 - 100.00")

    @Column(name = "commission")
    @NumberFormat(pattern = "#0,00")
    @JsonDeserialize(using = NumberDeserializers.BigDecimalDeserializer.class)
    @JsonSerialize(using = BigDecimalSerializer.class)
    BigDecimal commissionPt;

    @Id
    @Column(name = "currency_from")
    @JsonDeserialize(using = CurrencyDeserializer.class)
    @JsonSerialize(using = CurrencySerializer.class)
    @Enumerated(EnumType.STRING)
    @NotNull
    Currency from;

    @Id
    @Column(name = "currency_to")
    @JsonSerialize(using = CurrencySerializer.class)
    @JsonDeserialize(using = CurrencyDeserializer.class)
    @Enumerated(EnumType.STRING)
    @NotNull
    Currency to;
}

