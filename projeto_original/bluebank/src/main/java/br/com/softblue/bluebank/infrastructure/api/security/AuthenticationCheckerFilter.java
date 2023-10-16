package br.com.softblue.bluebank.infrastructure.api.security;

import static jakarta.ws.rs.Priorities.AUTHENTICATION;
import static jakarta.ws.rs.core.HttpHeaders.AUTHORIZATION;
import static jakarta.ws.rs.core.Response.Status.FORBIDDEN;

import java.io.IOException;

import jakarta.annotation.Priority;
import jakarta.inject.Inject;
import jakarta.ws.rs.container.ContainerRequestContext;
import jakarta.ws.rs.container.ContainerRequestFilter;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.Provider;

@Provider
@Authenticated
@Priority(AUTHENTICATION)
public class AuthenticationCheckerFilter implements ContainerRequestFilter {
	
	@Inject
	private JWTService jwtService;

	@Override
	public void filter(ContainerRequestContext requestContext) throws IOException {
		try {
			String token = extractTokenFromHeader(requestContext);

			AppJWT jwt = jwtService.verifyAndDecode(token);	
			requestContext.setSecurityContext(new JWTSecurityContext(jwt.userId()));
		} catch (Exception e) {
			requestContext.abortWith(Response.status(FORBIDDEN).build());
		}
	}
	
	private String extractTokenFromHeader(ContainerRequestContext requestContext) {
		return requestContext
			.getHeaderString(AUTHORIZATION)
			.substring("Bearer".length())
			.trim();
	}
}
