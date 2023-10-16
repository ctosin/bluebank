package br.com.softblue.bluebank.infrastructure.api.exception;

public class RequestException extends RuntimeException {
	
	private final ErrorCode errorCode;

	public RequestException(ErrorCode errorCode, String message) {
		super(message);
		this.errorCode = errorCode;
	}
	
	public RequestException(String message) {
		this(null, message);
	}
	
	public ErrorCode getErrorCode() {
		return errorCode;
	}
}
