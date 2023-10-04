package br.com.softblue.bluebank.infrastructure.api;

import static jakarta.ws.rs.core.MediaType.APPLICATION_JSON;

import java.util.List;

import br.com.softblue.bluebank.application.AccountService;
import br.com.softblue.bluebank.application.UserService;
import br.com.softblue.bluebank.domain.account.Account;
import br.com.softblue.bluebank.domain.user.User;
import br.com.softblue.bluebank.infrastructure.api.dto.CredentialsDTO;
import br.com.softblue.bluebank.infrastructure.api.dto.SaveUserDTO;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;

@Path("users")
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
	public CredentialsDTO createUser(SaveUserDTO saveUserDTO) {
		return userService.createUser(saveUserDTO);
	}
	
	@GET
	@Path("{userId}/accounts")
	@Produces(APPLICATION_JSON)
	public List<Account> getAccounts(@PathParam("userId") String userId) {
		return accountService.findAccountsByUserId(userId);
	}
	
	@GET
	@Path("{userId}/accounts/{accountId}")
	@Produces(APPLICATION_JSON)
	public Account getAccountByUserIdAndId(@PathParam("userId") String userId, @PathParam("accountId") Long accountId) {
		return accountService.findAccountByUserIdAndId(userId, accountId);
	}
}
