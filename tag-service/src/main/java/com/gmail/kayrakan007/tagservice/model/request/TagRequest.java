package com.gmail.kayrakan007.tagservice.model.request;

import jakarta.validation.constraints.NotBlank;

public record TagRequest(@NotBlank String tagName) {

}
