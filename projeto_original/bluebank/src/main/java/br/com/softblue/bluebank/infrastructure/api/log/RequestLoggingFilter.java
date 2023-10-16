package br.com.softblue.bluebank.infrastructure.api.log;

import static br.com.softblue.bluebank.util.SecurityUtils.getAuthUserId;
import static java.nio.charset.StandardCharsets.UTF_8;
import static org.apache.commons.lang3.StringUtils.isEmpty;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.stream.Collectors;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;

import jakarta.inject.Inject;
import jakarta.ws.rs.container.ContainerRequestContext;
import jakarta.ws.rs.container.ContainerRequestFilter;
import jakarta.ws.rs.core.SecurityContext;
import jakarta.ws.rs.ext.Provider;

@Provider
@Log
public class RequestLoggingFilter implements ContainerRequestFilter {
    
	@Inject
	private Logger logger;
	
	@Inject
	private SecurityContext securityContext;
    
	@Override
    public void filter(ContainerRequestContext context) {
		try {
			String body = extractRequestBody(context);
			logger.debug(
				"[userId={}] {} {} >>> {}",
				getAuthUserId(securityContext).orElse(null),
				context.getMethod(),
				context.getUriInfo().getPath(),
				isEmpty(body) ? "<no-body>" : body
			);
			context.setEntityStream(new ByteArrayInputStream(body.getBytes(UTF_8)));
			
		} catch (Exception e) {
			logger.warn("Request could not be logged due to an error", e);
		}
    }
	
	private String extractRequestBody(ContainerRequestContext context) throws IOException {
		return Arrays
			.stream(IOUtils.toString(context.getEntityStream(), UTF_8).split("\n"))
			.map(String::trim)
			.collect(Collectors.joining(" "));
	}
}
