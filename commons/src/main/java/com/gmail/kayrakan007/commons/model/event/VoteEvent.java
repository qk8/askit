package com.gmail.kayrakan007.commons.model.event;

public record VoteEvent(Long postId, String action, String voteTypeName) {
    
}
