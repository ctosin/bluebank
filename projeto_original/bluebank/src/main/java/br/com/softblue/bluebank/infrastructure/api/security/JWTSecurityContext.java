package br.com.softblue.bluebank.infrastructure.api.security;

import java.security.Principal;

import jakarta.ws.rs.core.SecurityContext;

public class JWTSecurityContext implements SecurityContext {

	private final String userId;
	
	public JWTSecurityContext(String userId) {
		this.userId = userId;
	}

	@Override
	public Principal getUserPrincipal() {
		return new AuthUser(userId);
	}

	@Override
	public boolean isUserInRole(String role) {
		return true;
	}

	@Override
	public boolean isSecure() {
		return true;
	}

	@Override
	public String getAuthenticationScheme() {
		return "AUTH";
	}
}
