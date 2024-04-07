package com.gmail.kayrakan007.postservice.model.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record PostRequest(

                @NotNull Long userId,
                Long parentId,
                String title,
                @NotBlank String text

) {

}
