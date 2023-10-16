package br.com.softblue.bluebank.infrastructure.api.security;

import java.security.Principal;

public class AuthUser implements Principal {
	
	private final String userId;

	public AuthUser(String userId) {
		this.userId = userId;
	}

	@Override
	public String getName() {
		return userId;
	}
}
