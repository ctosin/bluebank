package br.com.softblue.bluebank.infrastructure.api.log;

import static br.com.softblue.bluebank.util.SecurityUtils.getAuthUserId;

import org.slf4j.Logger;

import jakarta.inject.Inject;
import jakarta.ws.rs.container.ContainerRequestContext;
import jakarta.ws.rs.container.ContainerResponseContext;
import jakarta.ws.rs.container.ContainerResponseFilter;
import jakarta.ws.rs.core.SecurityContext;
import jakarta.ws.rs.ext.Provider;

@Provider
@Log
public class ResponseLoggingFilter implements ContainerResponseFilter {
    
	@Inject
	private Logger logger;
	
	@Inject
	private SecurityContext securityContext;
    
	@Override
    public void filter(ContainerRequestContext requestContext, ContainerResponseContext responseContext) {
		try {
			String body = responseContext.getEntity() != null ? responseContext.getEntity().toString() : null;
			logger.debug(
				"[userId={}] {} {} <<< {}",
				getAuthUserId(securityContext).orElse(null),
				requestContext.getMethod(),
				requestContext.getUriInfo().getPath(),
				body
			);

		} catch (Exception e) {
			logger.warn("Request could not be logged due to an error", e);
		}
    }
}
