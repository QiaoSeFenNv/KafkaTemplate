package com.qiaose.kafka;

import com.qiaose.entity.kafka.User;
import lombok.extern.slf4j.Slf4j;


import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class KafkaJsonProducer {

    private final KafkaTemplate<String, User> kafkaTemplate;

    public KafkaJsonProducer(KafkaTemplate<String, User> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendMessage(User data){
        log.info("send message to kafka {}" , data.toString());
        Message<User> javaGuides = MessageBuilder
                .withPayload(data)
                .setHeader(KafkaHeaders.TOPIC, "javaGuides_json")
                .build();

        kafkaTemplate.send(javaGuides);



    }
}
