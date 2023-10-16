package br.com.softblue.bluebank.infrastructure.api.exception;

import static br.com.softblue.bluebank.infrastructure.api.exception.ErrorCode.NOT_FOUND;

public class EntityNotFoundException extends RequestException {

	public EntityNotFoundException(String message) {
		super(NOT_FOUND, message);
	}
}
