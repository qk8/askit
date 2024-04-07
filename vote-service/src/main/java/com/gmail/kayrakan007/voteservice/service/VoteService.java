package com.gmail.kayrakan007.voteservice.service;

import com.gmail.kayrakan007.voteservice.model.request.VoteRequest;
import com.gmail.kayrakan007.voteservice.model.response.VoteResponse;

public interface VoteService {

    VoteResponse createVote(VoteRequest voteRequest);

    void deleteVote(Long voteId);
}
