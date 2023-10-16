package br.com.softblue.bluebank.infrastructure.api;

import static br.com.softblue.bluebank.util.SecurityUtils.getAuthUserId;
import static jakarta.ws.rs.core.MediaType.APPLICATION_JSON;
import static java.util.Objects.isNull;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.List;

import br.com.softblue.bluebank.application.service.AccountService;
import br.com.softblue.bluebank.application.service.UserService;
import br.com.softblue.bluebank.domain.user.User;
import br.com.softblue.bluebank.infrastructure.api.dto.AccountDTO;
import br.com.softblue.bluebank.infrastructure.api.dto.CredentialsDTO;
import br.com.softblue.bluebank.infrastructure.api.dto.MovementDTO;
import br.com.softblue.bluebank.infrastructure.api.dto.SaveMovementDTO;
import br.com.softblue.bluebank.infrastructure.api.dto.SaveUserDTO;
import br.com.softblue.bluebank.infrastructure.api.dto.UserDTO;
import br.com.softblue.bluebank.infrastructure.api.log.Log;
import br.com.softblue.bluebank.infrastructure.api.security.Authenticated;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.SecurityContext;

@Path("user")
@Log
public class UsersResource {
	
	@Inject
	private UserService userService;
	
	@Inject
	private AccountService accountService;
	
	@Inject
	private SecurityContext securityContext;

	@GET
	@Authenticated
	@Produces(APPLICATION_JSON)
	public UserDTO getUser() {
		return new UserDTO(userService.getById(getUserId()));
	}

	@POST
	@Produces(APPLICATION_JSON)
	public CredentialsDTO createUser(@Valid SaveUserDTO userToSave) {
		User user = userService.create(userToSave);
		return new CredentialsDTO(user.getId(), userToSave.getPassword());
	}
	
	@PUT
	@Authenticated
	@Produces(APPLICATION_JSON)
	public void updateUser(@Valid SaveUserDTO userToSave) {
		userService.update(getUserId(), userToSave.getName(), userToSave.getPassword(), userToSave.getEmail(), userToSave.getCpf());
	}
	
	@DELETE
	@Authenticated
	@Produces(APPLICATION_JSON)
	public void deleteUser() {
		userService.delete(getUserId());
	}
	
	@GET
	@Authenticated
	@Path("accounts")
	@Produces(APPLICATION_JSON)
	public List<AccountDTO> listAccounts() {
		return accountService
			.getAccountsByUserId(getUserId())
			.stream()
			.map(AccountDTO::new)
			.toList()
		;
	}
	
	@GET
	@Authenticated
	@Path("/accounts/{accountId}")
	@Produces(APPLICATION_JSON)
	public AccountDTO getAccount(@PathParam("accountId") @NotEmpty Long accountId) {
		return new AccountDTO(accountService.getAccountByUserIdAndId(getUserId(), accountId));
	}
	
	@POST
	@Authenticated
	@Path("accounts/{accountId}/movements")
	@Produces(APPLICATION_JSON)
	public Long createMovement(
		@PathParam("accountId") @NotNull Long accountId,
		@Valid SaveMovementDTO movement
	) {
		return accountService.createMovement(getUserId(), accountId, movement).getId();
	}
	
	@GET
	@Authenticated
	@Path("accounts/{accountId}/movements")
	@Produces(APPLICATION_JSON)
	public List<MovementDTO> getMovements(
		@PathParam("accountId") @NotNull Long accountId,
		@QueryParam("startDate") LocalDate startDate,
		@QueryParam("endDate") LocalDate endDate
	) {
		return accountService.listMovements(
				getUserId(),
				accountId,
				!isNull(startDate) ? startDate : YearMonth.now().atDay(1),
				!isNull(endDate) ? endDate : YearMonth.now().atEndOfMonth()
			)
			.stream()
			.map(MovementDTO::new)
			.toList();
	}
	
	private String getUserId() {
		return getAuthUserId(securityContext).orElseThrow();
	}
}
