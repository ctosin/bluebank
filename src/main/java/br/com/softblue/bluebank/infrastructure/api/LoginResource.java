package br.com.softblue.bluebank.infrastructure.api;

import static jakarta.ws.rs.core.MediaType.APPLICATION_JSON;

import br.com.softblue.bluebank.application.AuthService;
import br.com.softblue.bluebank.infrastructure.api.dto.CredentialsDTO;
import br.com.softblue.bluebank.infrastructure.api.log.Log;
import br.com.softblue.bluebank.infrastructure.api.security.LoginResultDTO;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;

@Path("login")
@Log
public class LoginResource {
	
	@Inject
	private AuthService authService;

	@POST
	@Consumes(APPLICATION_JSON)
	@Produces(APPLICATION_JSON)
	public LoginResultDTO login(CredentialsDTO credentials) {
		String jwt = authService.login(credentials.getId(), credentials.getPassword());
		return new LoginResultDTO(jwt);
	}
}
