package br.com.softblue.bluebank.infrastructure.web.view;

import static br.com.softblue.bluebank.domain.account.MovementType.DEBIT;
import static br.com.softblue.bluebank.util.NumberUtils.getFormattedCurrency;

import br.com.softblue.bluebank.domain.account.Movement;
import br.com.softblue.bluebank.util.DateUtils;

public class MovementView {

	private final Movement movement;

	public MovementView(Movement movement) {
		this.movement = movement;
	}
	
	public String getFormattedDate() {
		return DateUtils.getFormattedDate(movement.getDate());
	}
	
	public String getFormattedAmount() {
		return getFormattedCurrency(movement.getAmount());
	}
	
	public String getFormattedBalance() {
		return getFormattedCurrency(movement.getBalance());
	}
	
	public String getDescription() {
		return movement.getDescription();
	}
	
	public String getFormattedType() {
		if (movement.getType() == DEBIT) {
			return "Débito";
		} else {
			return "Crédito";
		}
	}
}
