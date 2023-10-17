package com.qiaose.controller;

import com.launchdarkly.eventsource.EventHandler;
import com.launchdarkly.eventsource.EventSource;

import com.qiaose.Handle.WikimediaChangeHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.util.concurrent.TimeUnit;

@Service
@Slf4j
public class WikimediaChangeProducer {

    private KafkaTemplate<String, String> kafkaTemplate;

    public WikimediaChangeProducer(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendMessage() throws InterruptedException {
        String topic = "wikimedia_recentchange";
        // to read real time stream data from wikimedia,we use event source

        EventHandler backgroundEventHandler = new WikimediaChangeHandler(kafkaTemplate, topic);

        String url = "https://stream.wikimedia.org/v2/stream/recentchange";

        EventSource.Builder evenBuilder = new EventSource.Builder(backgroundEventHandler, URI.create(url));

        EventSource eventSource = evenBuilder.build();

        eventSource.start();

        TimeUnit.MINUTES.sleep(10);


    }

}
