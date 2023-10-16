package br.com.softblue.bluebank.infrastructure.api.mapper;

import static jakarta.ws.rs.core.Response.Status.FORBIDDEN;

import br.com.softblue.bluebank.infrastructure.api.exception.AuthException;
import jakarta.ws.rs.core.Response.Status;
import jakarta.ws.rs.ext.Provider;

@Provider
public class AuthExceptionMapper extends AbstractExceptionMapper<AuthException> {

	@Override
	protected Status getStatus() {
		return FORBIDDEN;
	}
}
