package com.gmail.kayrakan007.voteservice.producer;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class KafkaProducer {

    private final KafkaTemplate<String, Object> kafkaTemplate;

    public KafkaProducer(KafkaTemplate<String, Object> kafkaTemplate) {
        
        this.kafkaTemplate = kafkaTemplate;
    }

    public void send(String topicName, Object message) {

        kafkaTemplate.send(topicName, message);

    }

}
