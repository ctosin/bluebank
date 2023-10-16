package br.com.softblue.bluebank.infrastructure.api.dto;

import java.math.BigDecimal;

import br.com.softblue.bluebank.domain.account.Movement;
import br.com.softblue.bluebank.domain.account.MovementType;

public record MovementDTO(
	Long id,
	String date,
	MovementType type,
	BigDecimal amount,
	BigDecimal balance,
	String description
) {

	public MovementDTO(Movement movement) {
		this(
			movement.getId(),
			movement.getDate().toString(),
			movement.getType(),
			movement.getAmount(),
			movement.getBalance(),
			movement.getDescription()
		);
	}
}
