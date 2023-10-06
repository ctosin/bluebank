package br.com.softblue.bluebank.infrastructure.api.log;

import static java.nio.charset.StandardCharsets.UTF_8;

import java.io.ByteArrayInputStream;
import java.io.IOException;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import jakarta.ws.rs.container.ContainerRequestContext;
import jakarta.ws.rs.container.ContainerRequestFilter;
import jakarta.ws.rs.ext.Provider;

@Provider
@Log
public class RequestLoggingFilter implements ContainerRequestFilter {

	private static final Logger logger = LoggerFactory.getLogger(RequestLoggingFilter.class);
	
	@Override
	public void filter(ContainerRequestContext context) throws IOException {
		String body = IOUtils.toString(context.getEntityStream(), UTF_8);
		logger.debug(">>> {} {} {}", context.getMethod(), context.getUriInfo().getPath(), body.isEmpty() ? "<empty>" : body);
		
		context.setEntityStream(new ByteArrayInputStream(body.getBytes(UTF_8)));
	}
}
