package br.com.softblue.bluebank.infrastructure.web.bean;

import static java.util.Objects.isNull;

import java.util.List;

import br.com.softblue.bluebank.application.service.AccountService;
import br.com.softblue.bluebank.infrastructure.web.security.LoggedUser;
import br.com.softblue.bluebank.infrastructure.web.view.AccountView;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

@Named
@RequestScoped
public class HomeBean {

	@Inject
	private LoggedUser loggedUser;
	
	@Inject
	private AccountService accountService;
	
	private List<AccountView> accounts;
	
	public List<AccountView> getAccounts() {
		if (isNull(accounts)) {
			accounts = accountService
				.getAccountsByUserId(loggedUser.getId())
				.stream()
				.map(AccountView::new)
				.toList();
		}
		return accounts;
	}
}
