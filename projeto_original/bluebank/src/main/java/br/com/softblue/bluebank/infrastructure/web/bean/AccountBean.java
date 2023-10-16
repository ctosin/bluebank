package br.com.softblue.bluebank.infrastructure.web.bean;

import static java.util.Objects.isNull;

import java.time.LocalDate;
import java.util.List;

import br.com.softblue.bluebank.application.service.AccountService;
import br.com.softblue.bluebank.infrastructure.web.security.LoggedUser;
import br.com.softblue.bluebank.infrastructure.web.view.MovementView;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

@Named
@RequestScoped
public class AccountBean {

	@Inject
	private AccountService accountService;
	
	@Inject
	private LoggedUser loggedUser;
	
	private Long accountId;
	
	private List<MovementView> movements;
	
	public Long getAccountId() {
		return accountId;
	}
	
	public void setAccountId(Long accountId) {
		this.accountId = accountId;
	}
	
	public List<MovementView> getMovements() {
		if (isNull(movements)) {
			movements = accountService
				.listMovements(loggedUser.getId(), accountId, LocalDate.now(), LocalDate.now())
				.stream()
				.map(MovementView::new)
				.toList();
		}
		return movements;
	}
}
