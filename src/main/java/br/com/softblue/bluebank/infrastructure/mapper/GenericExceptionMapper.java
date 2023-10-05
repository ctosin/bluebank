package br.com.softblue.bluebank.infrastructure.mapper;

import static jakarta.ws.rs.core.MediaType.APPLICATION_JSON_TYPE;
import static jakarta.ws.rs.core.Response.Status.BAD_REQUEST;
import static jakarta.ws.rs.core.Response.Status.INTERNAL_SERVER_ERROR;

import br.com.softblue.bluebank.domain.exception.RequestException;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

@Provider
public class GenericExceptionMapper implements ExceptionMapper<Exception> {

	@Override
	public Response toResponse(Exception exception) {
		exception.printStackTrace();
		Status status = getStatus(exception);
		
		return Response
			.status(status)
			.entity(
				new ResponseError(
					status.getStatusCode(),
					getErrorCode(exception),
					getErrorMessage(exception)
				)
			)
			.type(APPLICATION_JSON_TYPE)
			.build();
	}
	
	private Status getStatus(Exception exception) {
		if (exception instanceof RequestException) {
			return BAD_REQUEST;
		}
		
		return INTERNAL_SERVER_ERROR;
	}
	
	private String getErrorCode(Exception exception) {
		if (exception instanceof RequestException requestException) {
			return requestException.getErrorCode();
		}
		
		return null;
	}
	
	private String getErrorMessage(Exception exception) {
		if (exception instanceof RequestException) {
			return exception.getMessage();
		}
		
		return "Erro interno";
	}
	
	public static record ResponseError(int statusCode, String errorCode, String errorMessage) {}
}