package br.com.softblue.bluebank.domain.account;

import java.time.LocalDate;
import java.util.List;

import br.com.softblue.bluebank.domain.shared.AbstractRepository;
import jakarta.enterprise.context.RequestScoped;

@RequestScoped
public class MovementRepository extends AbstractRepository<Movement, Long> {
	
	public List<Movement> listMovementsByAccountIdAndTimeRange(
		Long accountId,
		LocalDate startDate,
		LocalDate endDate
	) {
		return query(
			"SELECT m FROM Movement m WHERE m.account.id = ?1 AND m.date >= ?2 AND m.date <= ?3 ORDER BY m.date",
			accountId,
			startDate,
			endDate
		);
	}

	@Override
	protected Class<Movement> getEntityClass() {
		return Movement.class;
	}
}
