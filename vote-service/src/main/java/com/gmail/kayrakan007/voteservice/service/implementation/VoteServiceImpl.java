package com.gmail.kayrakan007.voteservice.service.implementation;

import org.springframework.stereotype.Service;

import com.gmail.kayrakan007.commons.exception.PostNotFoundException;
import com.gmail.kayrakan007.commons.model.event.VoteEvent;
import com.gmail.kayrakan007.voteservice.client.PostClient;
import com.gmail.kayrakan007.voteservice.entity.Vote;
import com.gmail.kayrakan007.voteservice.exception.VoteNotFoundException;
import com.gmail.kayrakan007.voteservice.mapper.VoteMapper;
import com.gmail.kayrakan007.voteservice.model.request.VoteRequest;
import com.gmail.kayrakan007.voteservice.model.response.VoteResponse;
import com.gmail.kayrakan007.voteservice.producer.KafkaProducer;
import com.gmail.kayrakan007.voteservice.repository.VoteRepository;
import com.gmail.kayrakan007.voteservice.service.VoteService;

@Service
public class VoteServiceImpl implements VoteService {

    VoteRepository voteRepository;
    VoteMapper voteMapper;
    PostClient postClient;
    KafkaProducer kafkaProducer;

    public VoteServiceImpl(VoteRepository voteRepository, VoteMapper voteMapper, PostClient postClient,
            KafkaProducer kafkaProducer) {
        this.voteRepository = voteRepository;
        this.voteMapper = voteMapper;
        this.postClient = postClient;
        this.kafkaProducer = kafkaProducer;
    }

    @Override
    public VoteResponse createVote(VoteRequest voteRequest) {

        boolean postExists = postClient.existsById(voteRequest.postId());

        if (!postExists) {
            throw new PostNotFoundException();
        }

        Vote vote = voteMapper.voteRequestToVote(voteRequest);
        Vote createdVote = voteRepository.save(vote);
        VoteEvent voteEvent = new VoteEvent(createdVote.getPostId(),
                "created", createdVote.getVoteType().getName());
        kafkaProducer.send("voteEvent", voteEvent);
        VoteResponse createdVoteResponse = voteMapper.voteToVoteResponse(createdVote);

        return createdVoteResponse;

    }

    @Override
    public void deleteVote(Long voteId) {

        Vote voteFromDb = voteRepository.findById(voteId).orElseThrow(VoteNotFoundException::new);
        voteRepository.delete(voteFromDb);
        VoteEvent voteEvent = new VoteEvent(voteFromDb.getPostId(),
                "deleted", voteFromDb.getVoteType().getName());
        kafkaProducer.send("voteEvent", voteEvent);

    }

}
