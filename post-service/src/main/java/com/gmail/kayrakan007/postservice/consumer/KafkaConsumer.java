package com.gmail.kayrakan007.postservice.consumer;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import com.gmail.kayrakan007.commons.model.event.VoteEvent;
import com.gmail.kayrakan007.postservice.service.PostService;

@Component
public class KafkaConsumer {

    PostService postService;

    public KafkaConsumer(PostService postService) {
        this.postService = postService;
    }

    @KafkaListener(topics = "voteEvent", groupId = "voteEventListener", containerFactory = "kafkaListenerContainerFactory")
    public void voteEventListener(VoteEvent voteEvent) {

        postService.updatePost(voteEvent);
    }
}
