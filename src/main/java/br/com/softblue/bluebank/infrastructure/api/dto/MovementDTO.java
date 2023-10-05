package br.com.softblue.bluebank.infrastructure.api.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

import br.com.softblue.bluebank.domain.account.Movement;
import br.com.softblue.bluebank.domain.account.MovementType;

public class MovementDTO {

	private final Long id;
	
	private final LocalDate date;

	private final MovementType type;
	
	private final Integer amount;
	
	private final Integer balance;
	
	private final String description;
	
	public MovementDTO(Movement movement) {
		this.id = movement.getId();
		this.date = movement.getDate();
		this.type = movement.getType();
		this.amount = movement.getAmount().multiply(new BigDecimal(100)).intValue();
		this.balance = movement.getBalance().multiply(new BigDecimal(100)).intValue();
		this.description = movement.getDescription();
	}

	public Long getId() {
		return id;
	}

	public LocalDate getDate() {
		return date;
	}

	public MovementType getType() {
		return type;
	}

	public Integer getAmount() {
		return amount;
	}

	public Integer getBalance() {
		return balance;
	}

	public String getDescription() {
		return description;
	}
}
