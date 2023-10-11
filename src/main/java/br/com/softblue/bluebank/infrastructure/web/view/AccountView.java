package br.com.softblue.bluebank.infrastructure.web.view;

import static br.com.softblue.bluebank.infrastructure.util.NumberUtils.formatCurrency;

import br.com.softblue.bluebank.domain.account.Account;

public class AccountView {

	private final Account account;

	public AccountView(Account account) {
		this.account = account;
	}
	
	public Long getId() {
		return account.getId();
	}
	
	public String getFormattedType() {
//		switch(account.getType()) {
//			case CURRENT: return "Conta Corrente";
//			case SAVINGS: return "Conta Poupança";
//			default: return "<desconhecido>";
//		}
		
		return switch (account.getType()) {
			case CURRENT -> "Conta Corrente";
			case SAVINGS -> "Conta Poupança";
		};
	}
	
	public String getFormattedBalance() {
		return formatCurrency(account.getBalance());
	}
}
