package br.com.softblue.bluebank.infrastructure.api.log;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import jakarta.ws.rs.container.ContainerRequestContext;
import jakarta.ws.rs.container.ContainerResponseContext;
import jakarta.ws.rs.container.ContainerResponseFilter;
import jakarta.ws.rs.ext.Provider;

@Provider
@Log
public class ResponseLoggingFilter implements ContainerResponseFilter {
	
	private static final Logger logger = LoggerFactory.getLogger(ResponseLoggingFilter.class);
	
	@Override
	public void filter(ContainerRequestContext requestContext, ContainerResponseContext responseContext) throws IOException {
		String body = responseContext.getEntity() != null ? responseContext.getEntity().toString() : null;
		logger.debug("<<< {} {} {}", requestContext.getMethod(), requestContext.getUriInfo().getPath(), body);
	}
}
