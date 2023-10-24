package com.qiaose.configuration;

import org.apache.kafka.common.serialization.Deserializer;
import org.apache.kafka.common.serialization.Serde;
import org.apache.kafka.common.serialization.Serializer;
import org.apache.poi.ss.formula.functions.T;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.Map;

public class CustomDeserializer implements Deserializer<T> {
    @Override
    public void configure(Map<String, ?> configs, boolean isKey) {
        // 这里可以进行配置，但通常可以为空
    }

    @Override
    public T deserialize(String topic, byte[] data) {
        try {
            if (data != null) {
                ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(data);
                ObjectInputStream objectInputStream = new ObjectInputStream(byteArrayInputStream);
                T t = (T) objectInputStream.readObject();
                objectInputStream.close();
                return t;
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void close() {
        // 可选的关闭资源的逻辑
    }

}
