package br.com.softblue.bluebank.infrastructure.api.mapper;

import static jakarta.ws.rs.core.Response.Status.BAD_REQUEST;

import br.com.softblue.bluebank.infrastructure.api.exception.RequestException;
import jakarta.ws.rs.core.Response.Status;
import jakarta.ws.rs.ext.Provider;

@Provider
public class ClientRequestExceptionMapper extends AbstractExceptionMapper<RequestException> {

	@Override
	protected Status getStatus() {
		return BAD_REQUEST;
	}
}
