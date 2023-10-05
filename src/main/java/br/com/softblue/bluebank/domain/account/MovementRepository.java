package br.com.softblue.bluebank.domain.account;

import java.util.List;

import br.com.softblue.bluebank.domain.shared.AbstractRepository;
import jakarta.enterprise.context.RequestScoped;

@RequestScoped
public class MovementRepository extends AbstractRepository<Movement, Long> {

	@Override
	protected Class<Movement> getEntityClass() {
		return Movement.class;
	}
	
	public List<Movement> findByAccountId(Long accountId) {
		return query("SELECT m FROM Movement m WHERE m.account.id = ?1 ORDER BY m.date, m.id", accountId);
	}
}
