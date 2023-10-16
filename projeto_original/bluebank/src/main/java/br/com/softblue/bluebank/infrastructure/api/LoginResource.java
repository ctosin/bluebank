package br.com.softblue.bluebank.infrastructure.api;

import static jakarta.ws.rs.core.MediaType.APPLICATION_JSON;

import br.com.softblue.bluebank.application.service.AuthService;
import br.com.softblue.bluebank.infrastructure.api.dto.CredentialsDTO;
import br.com.softblue.bluebank.infrastructure.api.dto.LoginResultDTO;
import br.com.softblue.bluebank.infrastructure.api.log.Log;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;

@Path("login")
@Log
public class LoginResource {
	
	@Inject
	private AuthService userService;
	
	@POST
	@Produces(APPLICATION_JSON)
	public LoginResultDTO login(@Valid CredentialsDTO credentials) {
		String jwt = userService.loginById(credentials.userId(), credentials.password());
		return new LoginResultDTO(jwt);
	}
}
