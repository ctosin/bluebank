package br.com.softblue.bluebank.infrastructure.api;

import static jakarta.ws.rs.core.MediaType.APPLICATION_JSON;

import java.util.List;

import br.com.softblue.bluebank.application.AccountService;
import br.com.softblue.bluebank.application.UserService;
import br.com.softblue.bluebank.domain.user.User;
import br.com.softblue.bluebank.infrastructure.api.dto.AccountDTO;
import br.com.softblue.bluebank.infrastructure.api.dto.CredentialsDTO;
import br.com.softblue.bluebank.infrastructure.api.dto.MovementDTO;
import br.com.softblue.bluebank.infrastructure.api.dto.SaveMovementDTO;
import br.com.softblue.bluebank.infrastructure.api.dto.SaveUserDTO;
import br.com.softblue.bluebank.infrastructure.api.log.Log;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;

@Path("users")
@Log
public class UsersResource {

	@Inject
	private UserService userService;
	
	@Inject
	private AccountService accountService;

	@GET
	@Produces(APPLICATION_JSON)
	public List<User> getAll() {
		return userService.getAll();
	}
	
	@GET
	@Path("{userId}")
	@Produces(APPLICATION_JSON)
	public User getById(@PathParam("userId") String userId) {
		return userService.getById(userId);
	}
	
	@DELETE
	@Path("{userId}")
	public void deleteById(@PathParam("userId") String userId) {
		userService.deleteById(userId);
	}
	
	@PUT
	@Path("{userId}")
	public void updateUser(@PathParam("userId") String userId, SaveUserDTO saveUserDTO) {
		userService.updateUser(userId, saveUserDTO);
	}
	
	@POST
	@Consumes(APPLICATION_JSON)
	@Produces(APPLICATION_JSON)
	public CredentialsDTO createUser(@Valid SaveUserDTO saveUserDTO) {
		return userService.createUser(saveUserDTO);
	}
	
	@GET
	@Path("{userId}/accounts")
	@Produces(APPLICATION_JSON)
	public List<AccountDTO> getAccounts(@PathParam("userId") String userId) {
		return accountService
			.findAccountsByUserId(userId)
			.stream()
			.map(AccountDTO::new)
			.toList();
	}
	
	@GET
	@Path("{userId}/accounts/{accountId}")
	@Produces(APPLICATION_JSON)
	public AccountDTO getAccountByUserIdAndId(@PathParam("userId") String userId, @PathParam("accountId") Long accountId) {
		return new AccountDTO(accountService.findAccountByUserIdAndId(userId, accountId));
	}
	
	@GET
	@Path("{userId}/accounts/{accountId}/movements")
	@Produces(APPLICATION_JSON)
	public List<MovementDTO> getMovementsByUserIdAndAccountId(@PathParam("userId") String userId, @PathParam("accountId") Long accountId) {
		return accountService
			.findMovementsByUserIdAndAccountId(userId, accountId)
			.stream()
			.map(MovementDTO::new)
			.toList();
	}
	
	@POST
	@Path("{userId}/accounts/{accountId}/movements")
	@Consumes(APPLICATION_JSON)
	@Produces(APPLICATION_JSON)
	public Long createMovement(@PathParam("userId") String userId, @PathParam("accountId") Long accountId, SaveMovementDTO saveMovementDTO) {
		return accountService.createMovement(userId, accountId, saveMovementDTO).getId();
	}
}
