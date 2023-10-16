package br.com.softblue.bluebank.infrastructure.api.mapper;

import static jakarta.ws.rs.core.Response.Status.BAD_REQUEST;

import jakarta.validation.ConstraintViolationException;
import jakarta.ws.rs.core.Response.Status;
import jakarta.ws.rs.ext.Provider;

@Provider
public class ValidationExceptionMapper extends AbstractExceptionMapper<ConstraintViolationException> {

	@Override
	protected Status getStatus() {
		return BAD_REQUEST;
	}
}
