package br.com.softblue.bluebank.infrastructure.web.view;

import static br.com.softblue.bluebank.infrastructure.util.DateUtils.formatDate;
import static br.com.softblue.bluebank.infrastructure.util.NumberUtils.formatCurrency;

import br.com.softblue.bluebank.domain.account.Movement;

public class MovementView {

	private final Movement movement;

	public MovementView(Movement movement) {
		this.movement = movement;
	}
	
	public String getDescription() {
		return movement.getDescription();
	}
	
	public String getFormattedAmount() {
		return formatCurrency(movement.getAmount());
	}
	
	public String getFormattedBalance() {
		return formatCurrency(movement.getBalance());
	}
	
	public String getFormattedDate() {
		return formatDate(movement.getDate());
	}
	
	public String getFormattedType() {
		return switch (movement.getType()) {
			case DEBIT -> "Débito";
			case CREDIT -> "Crédito";
		};
	}
}
