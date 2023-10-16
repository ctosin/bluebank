package br.com.softblue.bluebank.infrastructure.api.mapper;

import static jakarta.ws.rs.core.Response.Status.INTERNAL_SERVER_ERROR;

import jakarta.ws.rs.core.Response.Status;
import jakarta.ws.rs.ext.Provider;

@Provider
public class GenericExceptionMapper extends AbstractExceptionMapper<Exception> {
	
	@Override
	protected Status getStatus() {
		return INTERNAL_SERVER_ERROR;
	}
	
	@Override
	protected void processException(Exception exception) {
		exception.printStackTrace();
	}
}
