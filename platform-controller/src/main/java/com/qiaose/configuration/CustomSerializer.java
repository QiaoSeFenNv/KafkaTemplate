package com.qiaose.configuration;

import org.apache.kafka.common.serialization.Serializer;
import org.apache.poi.ss.formula.functions.T;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.Map;

public class CustomSerializer implements Serializer<T> {
    @Override
    public void configure(Map<String, ?> configs, boolean isKey) {
        // 这里可以进行配置，但通常可以为空
    }

    @Override
    public byte[] serialize(String topic, T t) {
        try {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
            objectOutputStream.writeObject(t);
            objectOutputStream.close();
            return byteArrayOutputStream.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void close() {
        // 可选的关闭资源的逻辑
    }
}
