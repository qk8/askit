package com.gmail.kayrakan007.commons.exception;

import java.time.Instant;

public record ErrorMessage(int statusCode, Instant timestamp, String message, String description) {
}
