package com.gmail.kayrakan007.voteservice.mapper;

import org.springframework.stereotype.Component;

import com.gmail.kayrakan007.voteservice.entity.Vote;
import com.gmail.kayrakan007.voteservice.entity.VoteType;
import com.gmail.kayrakan007.voteservice.exception.VoteTypeNotFoundException;
import com.gmail.kayrakan007.voteservice.model.request.VoteRequest;
import com.gmail.kayrakan007.voteservice.model.response.VoteResponse;
import com.gmail.kayrakan007.voteservice.repository.VoteTypeRepository;

@Component
public class VoteMapper {

    VoteTypeRepository voteTypeRepository;

    public VoteMapper(VoteTypeRepository voteTypeRepository) {
        this.voteTypeRepository = voteTypeRepository;
    }

    public Vote voteRequestToVote(VoteRequest voteRequest) {

        Vote vote = new Vote();
        vote.setUserId(voteRequest.userId());
        vote.setPostId(voteRequest.postId());
        VoteType voteType = voteTypeRepository.findByName(voteRequest.voteTypeName())
                .orElseThrow(VoteTypeNotFoundException::new);
        vote.setVoteType(voteType);

        return vote;
    }

    public VoteResponse voteToVoteResponse(Vote vote) {

        VoteResponse voteResponse = new VoteResponse(vote.getId(), vote.getUserId(), vote.getPostId(),
                vote.getVoteType().getName());

        return voteResponse;

    }
}
