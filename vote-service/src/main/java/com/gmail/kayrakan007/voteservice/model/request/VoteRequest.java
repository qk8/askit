package com.gmail.kayrakan007.voteservice.model.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record VoteRequest(

                @NotNull Long userId,
                @NotNull Long postId,
                @NotBlank String voteTypeName

) {

}
