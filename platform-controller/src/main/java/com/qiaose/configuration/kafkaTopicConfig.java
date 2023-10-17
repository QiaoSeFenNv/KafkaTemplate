package com.qiaose.configuration;


import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class kafkaTopicConfig {

    @Bean
    public NewTopic javaGuidesTopic(){
        return TopicBuilder.name("javaGuides")
                .build();
    }

    @Bean
    public NewTopic javaGuidesJsonTopic(){
        return TopicBuilder.name("javaGuides_json")
                .build();
    }
}
