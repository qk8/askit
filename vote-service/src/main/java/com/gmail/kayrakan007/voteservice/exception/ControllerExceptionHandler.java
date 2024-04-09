package com.gmail.kayrakan007.voteservice.exception;

import java.time.Instant;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import com.gmail.kayrakan007.commons.exception.PostNotFoundException;
import com.gmail.kayrakan007.commons.exception.ErrorMessage;

@RestControllerAdvice
public class ControllerExceptionHandler {

	@ExceptionHandler(VoteNotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public ErrorMessage voteNotFoundException(VoteNotFoundException ex, WebRequest request) {

		ErrorMessage errorMessage = new ErrorMessage(
				HttpStatus.NOT_FOUND.value(),
				Instant.now(),
				ex.getMessage(),
				request.getDescription(false));

		return errorMessage;
	}

	@ExceptionHandler(VoteTypeNotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public ErrorMessage voteTypeNotFoundException(VoteTypeNotFoundException ex, WebRequest request) {

		ErrorMessage errorMessage = new ErrorMessage(
				HttpStatus.NOT_FOUND.value(),
				Instant.now(),
				ex.getMessage(),
				request.getDescription(false));

		return errorMessage;
	}

	@ExceptionHandler(PostNotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public ErrorMessage postNotFoundException(PostNotFoundException ex, WebRequest request) {

		ErrorMessage errorMessage = new ErrorMessage(
				HttpStatus.NOT_FOUND.value(),
				Instant.now(),
				ex.getMessage(),
				request.getDescription(false));

		return errorMessage;
	}

	@ExceptionHandler(Exception.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public ErrorMessage exception(Exception ex, WebRequest request) {

		ErrorMessage errorMessage = new ErrorMessage(
				HttpStatus.INTERNAL_SERVER_ERROR.value(),
				Instant.now(),
				ex.getMessage(),
				request.getDescription(false));

		return errorMessage;
	}

}
