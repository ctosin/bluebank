package br.com.softblue.bluebank.infrastructure.web.bean;

import java.math.BigDecimal;

import br.com.softblue.bluebank.application.AccountService;
import br.com.softblue.bluebank.domain.account.MovementType;
import br.com.softblue.bluebank.infrastructure.api.dto.SaveMovementDTO;
import br.com.softblue.bluebank.infrastructure.web.security.LoggedUserBean;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

@Named
@RequestScoped
public class CreateMovementBean {
	
	@Inject
	private AccountService accountService;
	
	@Inject
	private LoggedUserBean loggedUserBean;
	
	private SaveMovementDTO movement;
	private Long accountId;

	public String create() {
		accountService.createMovement(loggedUserBean.getId(), accountId, movement);
		return "account?faces-redirect=true&accountId=" + accountId;
	}
	
	public Long getAccountId() {
		return accountId;
	}
	
	public void setAccountId(Long accountId) {
		this.accountId = accountId;
	}

	public SaveMovementDTO getMovement() {
		if (movement == null) {
			movement = new SaveMovementDTO();
			movement.setType(MovementType.DEBIT.toString());
			movement.setAmount(BigDecimal.ZERO);
		}
		return movement;
	}

	public void setMovement(SaveMovementDTO movement) {
		this.movement = movement;
	}
}
