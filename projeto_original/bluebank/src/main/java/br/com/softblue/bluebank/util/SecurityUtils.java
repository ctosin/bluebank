package br.com.softblue.bluebank.util;

import static java.util.Objects.isNull;

import java.security.Principal;
import java.util.Optional;

import jakarta.ws.rs.core.SecurityContext;

public final class SecurityUtils {

	private SecurityUtils() {}
	
	public static Optional<String> getAuthUserId(SecurityContext context) {
		Principal principal = context.getUserPrincipal();
		if (!isNull(principal)) {
			return Optional.ofNullable(principal.getName());
		}
		return Optional.empty();
	}
}
