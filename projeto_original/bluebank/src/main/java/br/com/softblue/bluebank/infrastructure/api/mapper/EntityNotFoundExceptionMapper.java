package br.com.softblue.bluebank.infrastructure.api.mapper;

import static jakarta.ws.rs.core.Response.Status.NOT_FOUND;

import br.com.softblue.bluebank.infrastructure.api.exception.EntityNotFoundException;
import jakarta.ws.rs.core.Response.Status;
import jakarta.ws.rs.ext.Provider;

@Provider
public class EntityNotFoundExceptionMapper extends AbstractExceptionMapper<EntityNotFoundException> {

	@Override
	protected Status getStatus() {
		return NOT_FOUND;
	}
}
