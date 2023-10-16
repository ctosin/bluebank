package br.com.softblue.bluebank.infrastructure.web.bean;

import static br.com.softblue.bluebank.domain.account.MovementType.DEBIT;
import static java.util.Objects.isNull;

import br.com.softblue.bluebank.application.service.AccountService;
import br.com.softblue.bluebank.infrastructure.api.dto.SaveMovementDTO;
import br.com.softblue.bluebank.infrastructure.api.exception.RequestException;
import br.com.softblue.bluebank.infrastructure.web.security.LoggedUser;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.validation.Valid;

@Named
@RequestScoped
public class CreateMovementBean {
	
	@Inject
	private LoggedUser loggedUser;
	
	@Inject
	private AccountService accountService;

	@Valid
	private SaveMovementDTO movement;
	
	private Long accountId;
	
	private String message;
	
	public String create() {
		try {
			accountService.createMovement(loggedUser.getId(), accountId, movement);
		} catch (RequestException e) {
			message = e.getMessage();
			return null;
		}
		return "account?faces-redirect=true&accountId=" + accountId;
	}

	public SaveMovementDTO getMovement() {
		if (isNull(movement)) {
			movement = new SaveMovementDTO();
			movement.setType(DEBIT.toString());
		}
		return movement;
	}
	
	public void setMovement(SaveMovementDTO movement) {
		this.movement = movement;
	}
	
	public Long getAccountId() {
		return accountId;
	}
	
	public void setAccountId(Long accountId) {
		this.accountId = accountId;
	}
	
	public String getMessage() {
		return message;
	}
	
	public void setMessage(String message) {
		this.message = message;
	}
}
