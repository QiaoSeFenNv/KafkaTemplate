package com.qiaose.configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.common.serialization.Deserializer;
import org.apache.kafka.common.serialization.Serde;
import org.apache.kafka.common.serialization.Serializer;
import org.apache.poi.ss.formula.functions.T;

public class JsonSerde implements Serde<T> {
    private final ObjectMapper objectMapper = new ObjectMapper();
    @Override
    public Serializer<T> serializer() {
        return (topic, data) -> {
            try {
                return objectMapper.writeValueAsBytes(data);
            } catch (Exception e) {
                throw new RuntimeException("Error serializing MyObject to JSON", e);
            }
        };
    }

    @Override
    public Deserializer<T> deserializer() {
        return (topic, data) -> {
            try {
                return objectMapper.readValue(data, T.class);
            } catch (Exception e) {
                throw new RuntimeException("Error deserializing JSON to MyObject", e);
            }
        };
    }
}
