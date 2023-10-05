package br.com.softblue.bluebank.domain.exception;

@SuppressWarnings("serial")
public class RequestException extends RuntimeException {
	
	private String errorCode;

	public RequestException(String errorCode, String message, Throwable cause) { 
		super(message, cause);
		this.errorCode = errorCode;
	}

	public RequestException(String errorCode, String message) {
		this(errorCode, message, null);
	}
	
	public String getErrorCode() {
		return errorCode;
	}
}
