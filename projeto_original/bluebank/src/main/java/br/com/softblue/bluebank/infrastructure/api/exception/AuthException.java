package br.com.softblue.bluebank.infrastructure.api.exception;

import static br.com.softblue.bluebank.infrastructure.api.exception.ErrorCode.INVALID_CREDENTIALS;

public class AuthException extends RequestException {

	public AuthException() {
		super(INVALID_CREDENTIALS, "Invalid credentials");
	}
}
