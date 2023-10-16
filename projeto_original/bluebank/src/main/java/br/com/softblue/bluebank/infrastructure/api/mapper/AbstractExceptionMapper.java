package br.com.softblue.bluebank.infrastructure.api.mapper;

import static java.util.Objects.requireNonNull;

import java.util.Optional;

import br.com.softblue.bluebank.infrastructure.api.exception.ErrorCode;
import br.com.softblue.bluebank.infrastructure.api.exception.RequestException;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;
import jakarta.ws.rs.ext.ExceptionMapper;

public abstract class AbstractExceptionMapper<T extends Throwable> implements ExceptionMapper<T> {

	@Override
	public Response toResponse(T exception) {
		requireNonNull(exception);
		
		processException(exception);
		return Response
			.status(getStatus())
			.entity(
				new ResponseError(
					getStatus().getStatusCode(),
					getErrorCode(exception).map(ErrorCode::toString).orElse(null),
					getErrorMessage(exception)
				)
			)
			.build();
	}
	
	protected void processException(T exception) {
	}
	
	protected abstract Status getStatus();
	
	protected String getErrorMessage(T exception) {
		return exception.getMessage();
	}
	
	private Optional<ErrorCode> getErrorCode(T exception) {
		if (exception instanceof RequestException) {
			RequestException requestException = (RequestException) exception;
			return Optional.ofNullable(requestException.getErrorCode());
		}
		return Optional.empty();
	}
	
	public static record ResponseError(int statusCode, String errorCode, String errorMessage) { }
}
