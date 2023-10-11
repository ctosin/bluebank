package br.com.softblue.bluebank.infrastructure.web.bean;

import java.util.List;

import br.com.softblue.bluebank.application.AccountService;
import br.com.softblue.bluebank.infrastructure.web.security.LoggedUserBean;
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
	private LoggedUserBean loggedUserBean;
	
	private Long accountId;
	
	private List<MovementView> movements;
	
	public List<MovementView> getMovements() {
		if (movements == null) {
			movements = accountService
				.findMovementsByUserIdAndAccountId(loggedUserBean.getId(), accountId)
				.stream()
				.map(MovementView::new)
				.toList();
		}
		return movements;
	}
	
	public Long getAccountId() {
		return accountId;
	}
	
	public void setAccountId(Long accountId) {
		this.accountId = accountId;
	}
}
