package com.qiaose.kafka;



import com.qiaose.entity.kafka.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.Stack;

@Service
@Slf4j
public class KafkaJsonConsumer {

    @KafkaListener(topics = "javaGuides_json"  , groupId = "myGroup")
    public void consume(User user){
        log.info(String.format("Message send %s",user.toString()));
    }

}