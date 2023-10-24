package com.qiaose.stream;

import com.qiaose.configuration.CustomDeserializer;
import com.qiaose.configuration.CustomSerializer;
import com.qiaose.configuration.JsonSerde;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.Serde;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.StreamsConfig;
import org.apache.kafka.streams.kstream.Consumed;
import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.kstream.Produced;
import org.apache.poi.ss.formula.functions.T;

import java.util.HashMap;
import java.util.Properties;

public class dataSerialization {
    //序列化值：对象变为二进制
    //bytes to object
    //object to bytes

    /**
     * 首先，你创建了一个Properties对象(config)，用于存储Kafka Streams应用程序的配置参数。
     * 其中包括应用程序ID (APPLICATION_ID_CONFIG)，Kafka服务器的连接地址 (BOOTSTRAP_SERVERS_CONFIG)，
     * 以及消费者的偏移量设置 (AUTO_OFFSET_RESET_CONFIG)。
     * 创建StreamsBuilder：创建了一个StreamsBuilder对象(builder)，它用于构建Kafka Streams拓扑。
     * 创建输入流：使用builder.stream("input-topic", Consumed.with(Serdes.String(), Serdes.String()))来创建一个输入流(stream)，
     * 它会从名为"input-topic"的Kafka主题中读取数据。这个流的键和值都被解释为字符串(Serdes.String()用于序列化和反序列化)。
     * 数据处理：使用filter和mapValues操作对输入流进行处理。filter操作过滤出那些value以"ID5"开头的记录，
     * mapValues操作将value的数据类型从字符串转换为新的字符串。这两个操作创建了一个新的Kafka流(modifiedStream)，其中包含经过过滤和映射的数据。
     * 将处理后的数据写入输出主题：使用to操作将处理后的数据写入名为"output-topic"的Kafka主题，以便其他消费者可以订阅和处理这些数据。
     */
    public static void main(String[] args) {
        //这节课想表达的是 序列化
        Properties config = new Properties();
        config.put(StreamsConfig.APPLICATION_ID_CONFIG, "my-stream-processing-app");
        config.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, "kafka-broker1:9092,kafka-broker2:9092");
        config.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");

        StreamsBuilder builder = new StreamsBuilder();

        //普通使用
        KStream<String, String> streamString = builder.stream("input-topic", Consumed.with(Serdes.String(), Serdes.String()));

        KStream<String,String> modifiedStream = streamString.filter( (key,value)-> value.startsWith("ID5") )
                .mapValues(value->new String(value));
        modifiedStream.to("output-topic", Produced.with(Serdes.String(),Serdes.String()));

        //Serdes 对象 可以通过自定义来完成序列化的操作
        Serde<T> tSerde = Serdes.serdeFrom(new CustomSerializer(), new CustomDeserializer());
        //还可以通过实现serde接口
        KStream<String, T> streamSerde = builder.stream("input-topic", Consumed.with(Serdes.String(), new JsonSerde()));
        //不过，kafka提供了很多序列化的类，提供我们之间使用
        KStream<String, T> stream = builder.stream("input-topic", Consumed.with(Serdes.String(), tSerde));




    }

}
