package com.exchange.util.deserializer;

import com.exchange.model.Currency;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import lombok.Data;

import java.io.IOException;

@Data
public class CurrencyDeserializer extends JsonDeserializer<Currency> {

    @Override
    public Currency deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        ObjectCodec oc = p.getCodec();
        JsonNode node = oc.readTree(p);

        if (node == null) {
            return null;
        }

        String text = node.textValue();

        if (text == null) {
            return null;
        }

        return Currency.valueOf(text.trim().toUpperCase());
    }
}

