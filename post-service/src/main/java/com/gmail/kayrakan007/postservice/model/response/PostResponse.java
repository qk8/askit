package com.gmail.kayrakan007.postservice.model.response;

import java.time.Instant;

public record PostResponse(Long id, Long userId, Long parentId, String title, String text, Instant createdAt,
        Instant modifiedAt, Integer totalVotes) {

}
