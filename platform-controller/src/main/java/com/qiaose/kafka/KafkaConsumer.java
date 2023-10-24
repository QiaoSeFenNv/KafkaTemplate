package com.qiaose.kafka;



import com.qiaose.entity.kafka.User;
import lombok.extern.slf4j.Slf4j;
import lombok.var;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.StreamsConfig;
import org.apache.kafka.streams.kstream.Consumed;
import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.kstream.Produced;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.config.StreamsBuilderFactoryBean;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@Slf4j
public class KafkaConsumer {

    @KafkaListener(topics = "javaGuides"  , groupId = "myGroup")
    public void consume(String message){
        log.info(String.format("Message send %s",message));



//创建 kafka Stream 流
        StreamsBuilder streamsBuilder = new StreamsBuilder();
        //设置主题 和 序列化方式 得到KStream
        KStream<String, String> javaGuides = streamsBuilder.stream("javaGuides", Consumed.with(Serdes.String(), Serdes.String()));

        javaGuides.peek( (key,value) -> System.out.println("key "+key + " value "+ value) )
                .filter((key,value) -> value.contains("1") )
                .mapValues( (key,value)->value.substring( value.indexOf("-") +1 ) )
                .filter( (key,value) -> Long.parseLong(value)>1000 )
                .peek( (key,value) -> System.out.println("key "+key + " value "+ value) )
                .to("javaGuides2", Produced.with( Serdes.String(),Serdes.String()));
        Map<String, String> pros = new HashMap<>();
        StreamsConfig streamsConfig = new StreamsConfig(pros);

        try (KafkaStreams kafkaStreams = new KafkaStreams(streamsBuilder.build(), streamsConfig)) {

            kafkaStreams.start();
        }


    }

}
