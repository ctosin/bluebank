package br.com.softblue.bluebank.infrastructure.mapper;

import static jakarta.ws.rs.core.MediaType.APPLICATION_JSON_TYPE;
import static jakarta.ws.rs.core.Response.Status.BAD_REQUEST;
import static jakarta.ws.rs.core.Response.Status.FORBIDDEN;
import static jakarta.ws.rs.core.Response.Status.INTERNAL_SERVER_ERROR;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.com.softblue.bluebank.domain.exception.RequestException;
import br.com.softblue.bluebank.infrastructure.api.security.AuthException;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

@Provider
public class GenericExceptionMapper implements ExceptionMapper<Exception> {
	
	private static final Logger logger = LoggerFactory.getLogger(GenericExceptionMapper.class);

	@Override
	public Response toResponse(Exception exception) {
		logger.error("Ops!", exception);
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
		} else if (exception instanceof AuthException) {
			return FORBIDDEN;
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
		} else if (exception instanceof AuthException) {
			return null;
		}
		
		return "Erro interno";
	}
	
	public static record ResponseError(int statusCode, String errorCode, String errorMessage) {}
}
