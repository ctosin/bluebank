package br.com.softblue.bluebank.infrastructure.mapper;

import static jakarta.ws.rs.core.MediaType.APPLICATION_JSON_TYPE;
import static jakarta.ws.rs.core.Response.Status.BAD_REQUEST;

import java.util.stream.Collectors;

import br.com.softblue.bluebank.infrastructure.mapper.GenericExceptionMapper.ResponseError;
import jakarta.validation.ConstraintViolationException;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

@Provider
public class ValidationExceptionMapper implements ExceptionMapper<ConstraintViolationException> {

	@Override
	public Response toResponse(ConstraintViolationException exception) {
		return Response
			.status(BAD_REQUEST)
			.entity(
				new ResponseError(
					BAD_REQUEST.getStatusCode(),
					null,
					getFormattedMessage(exception)
				)
			)
			.type(APPLICATION_JSON_TYPE)
			.build();
	}
	
	private String getFormattedMessage(ConstraintViolationException exception) {
		return exception
			.getConstraintViolations()
			.stream()
			.map(v -> v.getMessage())
			.collect(Collectors.joining(" | ", "Erros de validação: ", ""));
	}
}
