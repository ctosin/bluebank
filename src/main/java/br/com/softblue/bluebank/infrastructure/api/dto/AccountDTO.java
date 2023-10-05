package br.com.softblue.bluebank.infrastructure.api.dto;

import java.math.BigDecimal;

import br.com.softblue.bluebank.domain.account.Account;
import br.com.softblue.bluebank.domain.account.AccountType;

public class AccountDTO {
	
	private final Long id;
	
	private final AccountType type;
	
	private final BigDecimal balance;

	public AccountDTO(Account account) {
		this.id = account.getId();
		this.type = account.getType();
		this.balance = account.getBalance();
	}

	public Long getId() {
		return id;
	}

	public AccountType getType() {
		return type;
	}

	public BigDecimal getBalance() {
		return balance;
	}
}
