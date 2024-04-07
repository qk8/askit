package com.gmail.kayrakan007.voteservice.controller;

import org.springframework.web.bind.annotation.RestController;

import com.gmail.kayrakan007.voteservice.model.request.VoteRequest;
import com.gmail.kayrakan007.voteservice.model.response.VoteResponse;
import com.gmail.kayrakan007.voteservice.service.VoteService;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
public class VoteController {

    VoteService voteService;

    public VoteController(VoteService voteService) {
        this.voteService = voteService;
    }

    @PostMapping    
    public VoteResponse createVote(@RequestBody VoteRequest voteRequest){
        return voteService.createVote(voteRequest);
    }

    @DeleteMapping("/{voteId}")
    public void deleteVote(@PathVariable("voteId") Long voteId){
        voteService.deleteVote(voteId);
    }
    
    
}
