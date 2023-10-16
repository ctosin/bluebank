package br.com.softblue.bluebank.infrastructure.web.view;

import static br.com.softblue.bluebank.domain.account.AccountType.CURRENT;
import static br.com.softblue.bluebank.util.NumberUtils.getFormattedCurrency;

import br.com.softblue.bluebank.domain.account.Account;

public class AccountView {

	private final Account account;

	public AccountView(Account account) {
		this.account = account;
	}
	
	public Long getId() {
		return account.getId();
	}
	
	public String getFormattedBalance() {
		return getFormattedCurrency(account.getBalance());
	}
	
	public String getFormattedType() {
		if (account.getType() == CURRENT) {
			return "Conta-Corrente";
		} else {
			return "Conta-Poupança";
		}
	}
}
