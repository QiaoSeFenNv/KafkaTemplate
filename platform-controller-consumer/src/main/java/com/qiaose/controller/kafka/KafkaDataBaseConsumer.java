package com.qiaose.controller.kafka;


import com.qiaose.entity.kafka.WikimediaData;
import com.qiaose.kakfa.respository.WikimediaDataRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class KafkaDataBaseConsumer {

    private final WikimediaDataRepository wikimediaDataRepository;

    public KafkaDataBaseConsumer(WikimediaDataRepository wikimediaDataRepository) {
        this.wikimediaDataRepository = wikimediaDataRepository;
    }

    @KafkaListener(
            topics = "wikimedia_recentchange" ,
            groupId = "myGroup")
    public void consume(String eventMessage){
        log.info("Message received -> {}",eventMessage);

        WikimediaData wikimediaData = new WikimediaData();
        wikimediaData.setWikiEventData(eventMessage);
        wikimediaDataRepository.save(wikimediaData);

    }
}
