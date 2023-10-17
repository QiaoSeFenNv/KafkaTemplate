package com.qiaose.kafka;



import com.qiaose.entity.kafka.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class KafkaConsumer {

    @KafkaListener(topics = "javaGuides"  , groupId = "myGroup")
    public void consume(String message){
        log.info(String.format("Message send %s",message));
    }

}
