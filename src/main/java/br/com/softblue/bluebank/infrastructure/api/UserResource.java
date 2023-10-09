package br.com.softblue.bluebank.infrastructure.api;

import static jakarta.ws.rs.core.MediaType.APPLICATION_JSON;

import br.com.softblue.bluebank.application.UserService;
import br.com.softblue.bluebank.domain.user.User;
import br.com.softblue.bluebank.infrastructure.api.log.Log;
import br.com.softblue.bluebank.infrastructure.api.security.Authenticated;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.SecurityContext;

@Path("user")
@Log
@Authenticated
public class UserResource {

	@Inject
	private UserService userService;
	
	@Inject
	private SecurityContext securityContext;
	
	@GET
	@Produces(APPLICATION_JSON)
	public User getLoggedUser() {
		return userService.getById(securityContext.getUserPrincipal().getName());
	}
}
