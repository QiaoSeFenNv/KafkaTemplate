package com.qiaose.controller.kafka;


import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class KafkaDataBaseConsumer {


    @KafkaListener(
            topics = "wikimedia_recentchange" ,
            groupId = "myGroup")
    public void consume(String eventMessage){
        log.info("Message received -> {}",eventMessage);

    }
}
