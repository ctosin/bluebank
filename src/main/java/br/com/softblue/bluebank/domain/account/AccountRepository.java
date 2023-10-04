package br.com.softblue.bluebank.domain.account;

import java.util.List;

import br.com.softblue.bluebank.domain.shared.AbstractRepository;
import jakarta.enterprise.context.RequestScoped;

@RequestScoped
public class AccountRepository extends AbstractRepository<Account, Long> {

	@Override
	protected Class<Account> getEntityClass() {
		return Account.class;
	}
	
	public List<Account> findAllByUserId(String userId) {
		return query("SELECT a FROM Account a WHERE a.user.id = ?1", userId);
	}
}
