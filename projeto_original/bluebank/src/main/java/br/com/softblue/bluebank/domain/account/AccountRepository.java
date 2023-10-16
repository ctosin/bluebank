package br.com.softblue.bluebank.domain.account;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import br.com.softblue.bluebank.domain.shared.AbstractRepository;
import jakarta.enterprise.context.RequestScoped;

@RequestScoped
public class AccountRepository extends AbstractRepository<Account, Long> {
	
	public List<Account> listAccountsByUserId(String userId) {
		return query("SELECT a FROM Account a WHERE a.user.id = ?1", userId);
	}
	
	public Optional<BigDecimal> getCurrentBalanceByAccountId(Long accountId) {
		return querySingle("SELECT a.balance FROM Account a WHERE a.id = ?1 ORDER BY a.id DESC", accountId);
	}

	@Override
	protected Class<Account> getEntityClass() {
		return Account.class;
	}
}
