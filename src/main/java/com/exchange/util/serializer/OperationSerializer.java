package com.exchange.util.serializer;

import com.exchange.model.OperationType;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;

public class OperationSerializer extends JsonSerializer<OperationType> {

    @Override
    public void serialize(OperationType value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
        gen.writeString(value.name());
    }
}
