package br.com.softblue.bluebank.infrastructure.web.bean;

import java.util.List;

import br.com.softblue.bluebank.application.AccountService;
import br.com.softblue.bluebank.infrastructure.web.security.LoggedUserBean;
import br.com.softblue.bluebank.infrastructure.web.view.AccountView;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

@Named
@RequestScoped
public class HomeBean {
	
	@Inject
	private AccountService accountService;
	
	@Inject
	private LoggedUserBean loggedUserBean;
	
	private List<AccountView> accounts;
	
	public List<AccountView> getAccounts() {
		if (accounts == null) {
//			List<Account> accounts = accountService.findAccountsByUserId(loggedUserBean.getId());
//			
//			this.accounts = new ArrayList<>();
//			for (Account account : accounts) {
//				this.accounts.add(new AccountView(account));
//			}
			
			accounts = accountService
				.findAccountsByUserId(loggedUserBean.getId())
				.stream()
				.map(AccountView::new)
				.toList();
			
		}
		return accounts;
	}
}
