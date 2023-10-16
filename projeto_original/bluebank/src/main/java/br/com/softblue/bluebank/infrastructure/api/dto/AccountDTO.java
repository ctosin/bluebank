package br.com.softblue.bluebank.infrastructure.api.dto;

import java.math.BigDecimal;

import br.com.softblue.bluebank.domain.account.Account;
import br.com.softblue.bluebank.domain.account.AccountType;

public record AccountDTO(
	Long id,
	AccountType type,
	BigDecimal balance
) {
	
	public AccountDTO(Account account) {
		this(account.getId(), account.getType(), account.getBalance());
	}
}
