package com.mtm.examples.graphql.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class MovieNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 7441940953134209357L;

	public MovieNotFoundException(String message) {
		super(message);
	}
}